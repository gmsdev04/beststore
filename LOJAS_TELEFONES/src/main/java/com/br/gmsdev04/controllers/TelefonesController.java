package com.br.gmsdev04.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.dto.TelefoneDto;
import com.br.gmsdev04.dto.TelefonePatchDto;
import com.br.gmsdev04.dto.TelefonePostDto;
import com.br.gmsdev04.entities.Loja;
import com.br.gmsdev04.entities.Telefone;
import com.br.gmsdev04.repository.LojasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class TelefonesController {

	private Logger LOGGER = Logger.getLogger(TelefonesController.class);

	@Autowired
	private LojasRepository lojas;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/apis/v1/lojas/{id_loja}/telefones")
	public ResponseEntity<ApiRoundTrip<TelefoneDto>> onPost(
			@PathVariable("id_loja") UUID idLoja,
			@Valid() @RequestBody() ApiRoundTrip<TelefonePostDto> request) {

		try {
			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {

				TelefonePostDto	telefoneDto = request.getData();
				Telefone novoTelefone = mapper.convertValue(telefoneDto,Telefone.class);
				Loja loja = lojaOptional.get();

				if(loja.getTelefones() != null && loja.getTelefones().size() > 0) {
					//DEMAIS TELEFONES

					//NAO POSSUI TELEFONE COM ESSE NUMERO
					if(loja.getTelefones().stream().noneMatch(x->x.getNumero() == telefoneDto.getNumero())) {
						loja.getTelefones().add(novoTelefone);
					}else {
						//POSSUI
						return ResponseEntity.badRequest().body(new ApiRoundTrip<>("Existe um telefone cadastrado com este número"));
					}
				}else {
					//PRIMEIRO TELEFONE
					Set<Telefone> telefones = new HashSet<>();
					telefones.add(novoTelefone);
					loja.setTelefones(telefones);
				}
				lojas.save(loja);
				return ResponseEntity.created(new URI("")).body(new ApiRoundTrip<>("Telefone criado",mapper.convertValue(novoTelefone,TelefoneDto.class)));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("A loja informada não existe"));
			}

		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}	

	@GetMapping("/apis/v1/lojas/{id_loja}/telefones")
	public ResponseEntity<ApiRoundTrip<List<TelefoneDto>>> getTelefonesByLoja(
			@PathVariable("id_loja") UUID idLoja) {
		List<TelefoneDto> telefonesEncontrados = new ArrayList<>();
		try {
			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {

				if(lojaOptional.get().getTelefones() != null)
					lojaOptional.get().getTelefones().forEach(x-> telefonesEncontrados.add(mapper.convertValue(x,TelefoneDto.class)));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("A loja informada não existe"));
			}
			return ResponseEntity.ok().body(new ApiRoundTrip<>("Telefones encontrados",telefonesEncontrados));
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}	
	
	@GetMapping("/apis/v1/lojas/{id_loja}/telefones/{id_telefone}")
	public ResponseEntity<ApiRoundTrip<TelefoneDto>> getTelefonesById(
			@PathVariable("id_loja") UUID idLoja,
			@PathVariable("id_telefone") UUID idTelefone) {
		try {
			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {

				Optional<Telefone> telefone = lojaOptional.get().getTelefones().stream().filter(x->x.getId().equals(idTelefone)).findFirst();
				
				if(telefone.isPresent()) {
					return ResponseEntity.ok().body(new ApiRoundTrip<>("Telefone encontrado",mapper.convertValue(telefone.get(),TelefoneDto.class)));
				}else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("O telefone informado não existe"));
				}
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("A loja informada não existe"));
			}
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}
}

package com.br.gmsdev04.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.dto.CaixaDto;
import com.br.gmsdev04.entities.Caixa;
import com.br.gmsdev04.entities.Loja;
import com.br.gmsdev04.repository.LojasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class CaixasController {

	private Logger LOGGER = Logger.getLogger(CaixasController.class);

	@Autowired
	private LojasRepository lojas;

	@Autowired
	private ObjectMapper mapper;

	@PostMapping("/apis/v1/lojas/{id_loja}/caixas")
	public ResponseEntity<ApiRoundTrip<CaixaDto>> onPost(@PathVariable("id_loja") UUID idLoja) {

		try {

			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {
				Loja loja = lojaOptional.get();
				Optional<Caixa> maiorCaixa = loja.getCaixas().stream().max(Comparator.comparing(Caixa::getNumero));
				Caixa novoCaixa = new Caixa();
				if(maiorCaixa.isPresent()) {
					novoCaixa.setNumero(maiorCaixa.get().getNumero()+1);
				}else {
					novoCaixa.setNumero(1);
				}
				loja.getCaixas().add(novoCaixa);

				lojas.save(loja);

				return ResponseEntity.created(new URI("/apis/v1/lojas/"+idLoja+"/caixas/"+novoCaixa.getId())).body(new ApiRoundTrip<CaixaDto>("Criado com sucesso", mapper.convertValue(novoCaixa,CaixaDto.class)));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrado"));
			}

		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/caixas")
	public ResponseEntity<ApiRoundTrip<List<CaixaDto>>> getCaixasByLoja(@PathVariable("id_loja") UUID idLoja) {
		List<CaixaDto> caixasEncontrados = new ArrayList<>();
		try {

			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {

				Loja loja = lojaOptional.get();

				if(loja.getCaixas() != null && loja.getCaixas().size() > 0) 
				{
					for(Caixa caixaDaVez : loja.getCaixas())
						caixasEncontrados.add(mapper.convertValue(caixaDaVez,CaixaDto.class));
				}

				return ResponseEntity.ok().body(new ApiRoundTrip<>("Caixas encontrados",caixasEncontrados));	
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrado"));
			}

		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}

	@GetMapping("/apis/v1/lojas/{id_loja}/caixas/{id_caixa}")
	public ResponseEntity<ApiRoundTrip<Caixa>> getCaixasById(@PathVariable("id_loja") UUID idLoja, @PathVariable("id_caixa") UUID idCaixa) {
		try {

			Optional<Loja> lojaOptional = lojas.findById(idLoja);

			if(lojaOptional.isPresent()) {

				Loja loja = lojaOptional.get();

				Optional<Caixa> caixaDoId = loja.getCaixas().stream().filter(x-> x.getId().equals(idCaixa)).findFirst();

				if(caixaDoId.isPresent()) {
					return ResponseEntity.ok().body(new ApiRoundTrip<>("Caixa encontrado",caixaDoId.get()));	
				}else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Caixa informado não existe nesta loja"));
				}

			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Loja informada não foi encontrado"));
			}

		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}



}

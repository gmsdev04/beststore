package com.br.gmsdev04.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.Validator;

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
import com.br.gmsdev04.dto.LojaDto;
import com.br.gmsdev04.entities.Loja;
import com.br.gmsdev04.repository.LojasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@RestController()
public class LojasController {

	private Logger LOGGER = Logger.getLogger(LojasController.class);

	@Autowired
	private LojasRepository lojas;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private Validator validator;
	
	@PostMapping("/apis/v1/lojas")
	public ResponseEntity<ApiRoundTrip<LojaDto>> onPost(@Valid() @RequestBody() ApiRoundTrip<LojaDto> request) {

		try {
			LojaDto data = request.getData();

			Loja novaLoja = mapper.convertValue(data,Loja.class);

			lojas.save(novaLoja);
			
			return ResponseEntity.created(new URI("/apis/v1/lojas/"+novaLoja.getId()))
					.body(new ApiRoundTrip<LojaDto>("Criado com sucesso", mapper.convertValue(novaLoja,LojaDto.class)));
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}
	
	@GetMapping("/apis/v1/lojas/{id}")
	public ResponseEntity<ApiRoundTrip<LojaDto>> onGet(@PathVariable("id") UUID id){
		try {
			Optional<Loja> loja = lojas.findById(id);
			
			if(loja.isPresent()) {
				LojaDto dto = mapper.convertValue(loja,LojaDto.class);
				
				return ResponseEntity.ok().body(new ApiRoundTrip<>("Loja encontrada com sucesso",dto)); 
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Recurso não encontrado"));
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}
	
	@PatchMapping("/apis/v1/lojas/{id}")
	public ResponseEntity<ApiRoundTrip<LojaDto>> onPatch(@PathVariable("id") UUID id,@RequestBody ApiRoundTrip<Object> request){
		try {
			
			Optional<Loja> lojaDb = lojas.findById(id);
			
			if(lojaDb.isPresent()) {
				ObjectReader reader = mapper.reader();
				reader.withValueToUpdate(lojaDb);
				reader.readValue(request.getData().toString());
				
				
				
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRoundTrip<>("Recurso não encontrado"));
		}catch(Exception e) {
			LOGGER.error("Falha ao executar post loja message -> "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRoundTrip<>("Falha ao processar solicitação"));
		}
	}
	
}

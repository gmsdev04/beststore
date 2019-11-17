package com.br.gmsdev04.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.dto.LojaDto;
import com.br.gmsdev04.repository.LojasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class LojasController {

	@Autowired
	private LojasRepository lojas;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/apis/v1/lojas")
	public ResponseEntity<ApiRoundTrip<LojaDto>> onPost(@Valid() @RequestBody() ApiRoundTrip<LojaDto> request) {
		
		LojaDto data = request.getData();
		
		
		
		return ResponseEntity.ok().build(); // IS WRONG
	}
	
}

package com.br.gmsdev04.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class EmailDto {
	
	@JsonProperty("id")
	private UUID id;
	@JsonProperty("endereco")
	@Email()
	private String endereco;
	@JsonProperty("principal")
	private boolean principal;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCriacao;
	@JsonProperty("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
	
}

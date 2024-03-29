package com.br.gmsdev04.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class TelefoneDto {
	@JsonProperty("id")
	private UUID id;
	@JsonProperty("numero") @Min(value=1,message= "O telefone deve conter um número")
	private int numero;
	@JsonProperty("ddd") @Min(value=1,message= "O telefone deve conter um DDD")
	private int ddd;
	@JsonProperty("ddi") @Min(value=1,message="O telefone deve conter um DDI")
	private int ddi;
	@JsonProperty("principal")
	private boolean principal;
	@JsonProperty("tipo") @Min(value=1,message="O telefone deve conter um tipo")
	private int tipo;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCriacao;
	@JsonProperty("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
}

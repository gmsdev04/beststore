package com.br.gmsdev04.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class TelefonePatchDto {
	@JsonProperty("numero") @Min(value=1,message= "O telefone deve conter um número")
	private Integer numero;
	@JsonProperty("ddd") @Min(value=1,message= "O telefone deve conter um DDD")
	private Integer ddd;
	@JsonProperty("ddi") @Min(value=1,message="O telefone deve conter um DDI")
	private Integer ddi;
	@JsonProperty("principal")
	private boolean principal;
	@JsonProperty("tipo") @Min(value=1,message="O telefone deve conter um tipo")
	private Integer tipo;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCriacao;
	@JsonProperty("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
}

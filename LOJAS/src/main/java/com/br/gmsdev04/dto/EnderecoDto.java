package com.br.gmsdev04.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class EnderecoDto {	
	
	@JsonProperty("id")
	private UUID id;
	@NotNull(message="O endereço deve conter um logradouro")
	@Size(min = 1, max = 50, message = "O logradouro deve conter de 1 a 50 caracteres.")
	@JsonProperty("logradouro")
	private String logradouro;
	@JsonProperty("pais")
	@NotNull(message="O endereço deve conter um pais")
	private String pais;
	@JsonProperty("estado")
	private String estado;
	@JsonProperty("cidade")
	private String cidade;
	@JsonProperty("municipio")
	private String municipio;
	@JsonProperty("uf")
	@NotNull(message="O endereço deve conter UF")
	private String uf;
	@JsonProperty("complemento")
	private String complemento;
	@JsonProperty("numero")
	@NotNull(message="O endereço deve conter um número")
	private int numero;
	@NotNull(message="O endereço deve conter um cep")
	@JsonProperty("cep")
	private int cep;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCriacao;
	@JsonProperty("ultima_atualizacao")
	private String ultimaAtualizacao;
}


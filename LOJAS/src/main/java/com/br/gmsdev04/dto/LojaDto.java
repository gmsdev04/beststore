package com.br.gmsdev04.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;


@Data()
@JsonInclude(Include.NON_NULL)
public class LojaDto {

	@JsonProperty("id")
	private UUID id;
	@JsonProperty("nome")
	@NotNull(message = "A loja deve conter um nome")
	private String nome;
	@JsonProperty("endereco")
	@NotNull(message = "A loja deve conter endereço")
	@Valid()
	private EnderecoDto endereco;
	@JsonProperty("emails") 
	@Valid()
	private Set<EmailDto> emails;
	@JsonProperty("telefones") 
	@Valid()
	private Set<TelefoneDto> telefones;
	@JsonProperty(value = "caixas",access=Access.READ_WRITE)
	private Set<CaixaDto> caixas;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCriacao;
	@JsonProperty("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
}

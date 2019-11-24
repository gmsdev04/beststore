package com.br.gmsdev04.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@JsonInclude(Include.NON_NULL)
public class CaixaDto {
	
		@JsonProperty("id")
		private UUID id;
		@JsonProperty("numero")
		private int numero;
		@JsonProperty("client_key")
		private UUID clientKey;	
		@Column("instante_criacao")
		private LocalDateTime instanteCriacao;
}

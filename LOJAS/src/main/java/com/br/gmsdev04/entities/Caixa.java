package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("caixa")
public class Caixa {
	
	@Column("id")
	private UUID id;
	@Column("numero")
	private int numero;
	@Column("client_key")
	private UUID clientKey;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	
	public Caixa() {
		this.id = UUID.randomUUID();
		this.instanteCriacao = LocalDateTime.now();
	}
}

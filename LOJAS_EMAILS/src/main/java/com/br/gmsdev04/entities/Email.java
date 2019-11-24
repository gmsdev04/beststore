package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data()
@UserDefinedType("email")
public class Email {
	
	@Column("id")
	private UUID id;
	@Column("endereco")
	private String endereco;
	@Column("principal")
	private boolean principal;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	@Column("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
	
	public Email() {
		this.id = UUID.randomUUID();
		this.instanteCriacao = LocalDateTime.now();
	}
}

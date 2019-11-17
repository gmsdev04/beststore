package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data()
@UserDefinedType("telefone")
public class Telefone {
	@Column("id")
	private UUID id;
	@Column("numero")
	private int numero;
	@Column("ddd")
	private int ddd;
	@Column("ddi")
	private int ddi;
	@Column("principal")
	private boolean principal;
	@Column("tipo")
	private int tipo;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	@Column("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
}

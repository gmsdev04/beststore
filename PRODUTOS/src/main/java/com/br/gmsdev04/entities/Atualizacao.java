package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("atualizacao")
public class Atualizacao {

	@Column("id")
	private UUID id;
	@Column("titulo")
	private String titulo;
	@Column("descricao")
	private String descricao;
	@Column("atualizador")
	private String atualizador;
	@Column("alteracoes")
	private Map<String, String> alteracoes;
	@Column("instante_atualizacao")
	private LocalDateTime instante_atualizacao;

	public Atualizacao() {
		this.id = UUID.randomUUID();
		this.instante_atualizacao = LocalDateTime.now();
	}
}

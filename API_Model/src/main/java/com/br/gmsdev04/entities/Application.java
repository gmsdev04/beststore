package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Table("applications")
@Data
public class Application {
	
	//ATRIBUTTES
	@PrimaryKey("client_id")
	private UUID clientId;
	@Column("client_secret")
	private UUID clientSecret;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	@Column("ativo")
	private boolean ativo;
	
	public Application() {
		this.clientId = UUID.randomUUID();
		this.clientSecret = UUID.randomUUID();
		this.instanteCriacao = LocalDateTime.now();
		this.ativo = true;
	}
}

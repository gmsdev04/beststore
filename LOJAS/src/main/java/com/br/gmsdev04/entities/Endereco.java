package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data()
@UserDefinedType("endereco")
public class Endereco {	
	
	@Column("id")
	private UUID id;
	@Column("logradouro")
	private String logradouro;
	@Column("pais")
	private String pais;
	@Column("estado")
	private String estado;
	@Column("cidade")
	private String cidade;
	@Column("municipio")
	private String municipio;
	@Column("uf")
	private String uf;
	@Column("complemento")
	private String complemento;
	@Column("numero")
	private int numero;
	@Column("cep")
	private int cep;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	@Column("ultima_atualizacao")
	private String ultimaAtualizacao;
}

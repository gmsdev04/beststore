package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.Data;


@Data()
@Table("lojas")
public class Loja {
	
	@PrimaryKey("id")
	private UUID id;
	private String nome;
	@CassandraType(type = DataType.Name.UDT, userTypeName = "endereco") 
	private Endereco endereco;
	@CassandraType(type = DataType.Name.UDT, userTypeName = "email") 
	private Set<Email> emails;
	@CassandraType(type = DataType.Name.UDT, userTypeName = "telefone") 
	private Set<Telefone> telefones;
	@CassandraType(type = DataType.Name.UDT, userTypeName = "caixa") 	
	private Set<Caixa> caixas;
	@Column("instante_criacao")
	private LocalDateTime instanteCriacao;
	@Column("ultima_atualizacao")
	private LocalDateTime ultimaAtualizacao;
	public Loja() {
		this.id = UUID.randomUUID();
		this.instanteCriacao = LocalDateTime.now();
	}
}

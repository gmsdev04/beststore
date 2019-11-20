package com.br.gmsdev04.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("produtos")
public class Produto {
	
	@PrimaryKey
	private ProdutoKey id;
	@Column("nome")
	private String nome;
	@Column("valor")
	private double valor;
	@Column("quantidade")
	private int quantidade;
	@Column("categoria")
	private UUID categoria;
	@Column("fabricante")
	private UUID fabricante;
	@Column("data_fabricacao")
	private LocalDate dataFabricacao;
	@Column("data_validade")
	private LocalDate dataValidade;
	@Column("imagem")
	private byte[] imagem;
	@Column("descricao")
	private String descricao;
	@Column("ativo")
	private boolean ativo;
	@Column("instante_criacao")
	private LocalDateTime instanteCricacao;
	@Column("atualizacoes")
	private Set<Atualizacao> atualizacoes;
	
}

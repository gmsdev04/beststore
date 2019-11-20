package com.br.gmsdev04.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.br.gmsdev04.entities.Atualizacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class ProdutoDto {
	
	@JsonProperty("id_loja")
	private UUID idLoja;
	@JsonProperty("id_produto")
	private UUID idProduto;
	@JsonProperty("nome")
	@NotNull()
	private String nome;
	@JsonProperty("valor")
	private double valor;
	@JsonProperty("quantidade")
	@Min(1)
	private int quantidade;
	@JsonProperty("categoria")
	private UUID categoria;
	@JsonProperty("fabricante")
	private UUID fabricante;
	@JsonProperty("data_fabricacao")
	private LocalDate dataFabricacao;
	@JsonProperty("data_validade")
	private LocalDate dataValidade;
	@JsonProperty("imagem")
	private byte[] imagem;
	@JsonProperty("descricao")
	private String descricao;
	@JsonProperty("ativo")
	private boolean ativo;
	@JsonProperty("instante_criacao")
	private LocalDateTime instanteCricacao;
	@JsonProperty("atualizacoes")
	private Set<Atualizacao> atualizacoes;
	
}

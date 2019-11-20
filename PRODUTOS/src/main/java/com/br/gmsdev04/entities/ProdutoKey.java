package com.br.gmsdev04.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.Data;

@Data()
@PrimaryKeyClass()
public class ProdutoKey implements Serializable{

	private static final long serialVersionUID = 4831105889991485819L;
	
	@PrimaryKeyColumn(name = "idLoja", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private UUID idLoja;
	@PrimaryKeyColumn(name = "idProduto", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private UUID idProduto;
	
	public ProdutoKey(UUID idLoja, UUID idProduto) {
		this.idLoja = idLoja;
		this.idProduto = idProduto;
	}
	
	public ProdutoKey() {
		
	}
}

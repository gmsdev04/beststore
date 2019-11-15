package com.br.gmsdev04.entities;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Endereco {
	private ObjectId id;
	private String logradouro;
	private String pais;
	private String cidade;
	private String estado;
	private int numero;
	private String complemento;
	private String cep;
}

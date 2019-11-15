package com.br.gmsdev04.entities;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Loja")
public class Loja {
	private ObjectId id;
	private Endereco endereco;
	private boolean ativa;
}

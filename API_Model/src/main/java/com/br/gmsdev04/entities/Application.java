package com.br.gmsdev04.entities;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("application")
@Data
public class Application {
	
	//ATRIBUTTES
	@Id
	private ObjectId clientId;
	private ObjectId clientSecret;
	private LocalDateTime instanteCriacao;
	private boolean isAtivo;
	
	public Application() {
		this.clientId = ObjectId.get();
		this.clientSecret = ObjectId.get();
		this.instanteCriacao = LocalDateTime.now();
		this.isAtivo = true;
	}
}

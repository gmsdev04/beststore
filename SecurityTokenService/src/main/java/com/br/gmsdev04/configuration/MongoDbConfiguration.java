package com.br.gmsdev04.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoDbConfiguration {

	@Value("${mongodb.uri}")
	private String mongodbUri;

	@Bean
	public  MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(mongodbUri);
		return new MongoClient(uri);
	}


}

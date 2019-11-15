package com.br.gmsdev04.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.gmsdev04.entities.Application;

public interface ApplicationRepository extends MongoRepository<Application, String>{

}

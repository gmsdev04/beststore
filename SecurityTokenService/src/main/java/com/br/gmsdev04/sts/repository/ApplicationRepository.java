package com.br.gmsdev04.sts.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.gmsdev04.sts.entities.Application;

public interface ApplicationRepository extends MongoRepository<Application, String>{

}

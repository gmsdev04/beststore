package com.br.gmsdev04.sts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.gmsdev04.sts.entities.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session,String> {

	String findByJwtHash(String jwtHash);	
	
}

package com.br.gmsdev04.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.gmsdev04.entities.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token,String> {

	String findByJwtHash(String jwtHash);	
	
}

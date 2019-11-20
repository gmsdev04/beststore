package com.br.gmsdev04.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.br.gmsdev04.entities.Loja;

public interface LojasRepository extends CassandraRepository<Loja,UUID> {

}

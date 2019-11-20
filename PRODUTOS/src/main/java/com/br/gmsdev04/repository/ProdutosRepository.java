package com.br.gmsdev04.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.br.gmsdev04.entities.Produto;
import com.br.gmsdev04.entities.ProdutoKey;

public interface ProdutosRepository extends CassandraRepository<Produto,ProdutoKey>{
	
	@Query("select * from beststore.produtos where idLoja =?0")
	public List<Produto> findByIdLoja(UUID idLoja);
}

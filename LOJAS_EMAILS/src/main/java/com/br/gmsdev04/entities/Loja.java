package com.br.gmsdev04.entities;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.Data;


@Data()
@Table("lojas")
public class Loja {
	
	@PrimaryKey("id")
	private UUID id;
	@CassandraType(type = DataType.Name.UDT, userTypeName = "email") 
	private Set<Email> emails;
}

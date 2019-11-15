package com.br.gmsdev04.sts.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@RedisHash("sess")
@Data
public class Session {

	@Id
	@Indexed
	private String jwtHash;
	
}

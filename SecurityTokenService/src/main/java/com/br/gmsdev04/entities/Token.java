package com.br.gmsdev04.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@RedisHash("tokens")
public class Token {

	@Id
	@JsonProperty("token")
	private String token;
	@JsonProperty("expire_instant")
	private LocalDateTime expireInstant;
	@JsonProperty("expires_in")
	@TimeToLive
	private int expiresIn;
	
	public Token() {
		super();
		this.token = new StringBuilder()
				.append(UUID.randomUUID().toString()).append(".")
				.append(UUID.randomUUID().toString()).append(".")
				.append(UUID.randomUUID().toString()).toString();
		this.expireInstant = LocalDateTime.now().plusSeconds(300);
		this.expiresIn = 300;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expireInstant);
	}
}

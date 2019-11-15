package com.br.gmsdev04.sts.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

	@Value("${redis.hostname}")
	private String hostName;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.port}")
	private int port;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		
		redisStandaloneConfiguration.setHostName(this.hostName);
		redisStandaloneConfiguration.setPort(this.port);
		redisStandaloneConfiguration.setPassword(this.password);
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}
	
    @Bean

    public RedisTemplate<?, ?> redisTemplate() {

        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory());

        return template;

    }

}

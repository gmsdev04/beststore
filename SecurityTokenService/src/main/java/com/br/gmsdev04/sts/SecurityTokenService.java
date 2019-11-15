package com.br.gmsdev04.sts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.br.gmsdev04.sts")
public class SecurityTokenService {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTokenService.class, args);
	}

}

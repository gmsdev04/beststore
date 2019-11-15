package com.br.gmsdev04.sts.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.sts.entities.Application;
import com.br.gmsdev04.sts.entities.Session;
import com.br.gmsdev04.sts.repository.ApplicationRepository;
import com.br.gmsdev04.sts.repository.SessionRepository;

@RestController("/sts/v1/tokens")
public class TokenController {

	@Autowired
	private SessionRepository sessions;

	@Autowired
	private ApplicationRepository applications;
	
	@GetMapping()
	public ResponseEntity<ApiRoundTrip> onGet(
			@RequestHeader("client_id") String clientId,
			@RequestHeader("client_secret") String clientSecret,
			@RequestHeader("grant_type") String grantType
			)
	{
		if( (clientId == null     || clientId.isEmpty())	 ||
		    (clientSecret == null || clientSecret.isEmpty()) ||
		    (grantType == null    || grantType.isEmpty()) 	
		  )
			return ResponseEntity.badRequest().body(new ApiRoundTrip("cliend_id, client_secret and grant_type can not be null or empty"));

		
		Application ap = new Application();
		
		applications.save(ap);
		
		Session s = new Session();
		String id = UUID.randomUUID().toString();
		s.setJwtHash(id);
		
		sessions.save(s);
		
		Optional<Session> ss = sessions.findById(id);

		
		return null;
	}

}

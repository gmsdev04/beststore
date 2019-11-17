package com.br.gmsdev04.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.br.gmsdev04.commons.ApiRoundTrip;
import com.br.gmsdev04.entities.Application;
import com.br.gmsdev04.entities.Token;
import com.br.gmsdev04.repository.ApplicationRepository;
import com.br.gmsdev04.repository.TokenRepository;

@RestController()
public class TokenController {

	@Autowired
	private TokenRepository tokens;

	@Autowired
	private ApplicationRepository applications;
	
	@PostMapping("/sts/v1/tokens")
	public ResponseEntity<ApiRoundTrip> onPost(
			@RequestHeader("client_id") UUID clientId,
			@RequestHeader("client_secret") UUID clientSecret,
			@RequestHeader("grant_type") String grantType
			)
	{
		if( (clientId == null )	 ||
		    (clientSecret == null) ||
		    (grantType == null    || grantType.isEmpty()) 	
		  )
			return ResponseEntity.badRequest().body(new ApiRoundTrip("cliend_id, client_secret and grant_type can not be null or empty"));
		
		if(grantType.equals("password")) 
		{
			Optional<Application> apl = applications.findById(clientId);
			
			if(apl.isPresent()) {
				Application application = apl.get();
				
				if(application.getClientSecret().equals(clientSecret)) {
					Token token = new Token();
					
					tokens.save(token);
					
					return ResponseEntity.created(null).body(new ApiRoundTrip("Token gerado com sucesso", token));
				}
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}else if (grantType.equals("refresh_token")) 
		{
			
		}
		
		return ResponseEntity.badRequest().body(new ApiRoundTrip("grant_type inválid"));
	}

}

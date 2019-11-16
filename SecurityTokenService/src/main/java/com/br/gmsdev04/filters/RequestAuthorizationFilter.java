package com.br.gmsdev04.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.br.gmsdev04.entities.Token;
import com.br.gmsdev04.repository.TokenRepository;


public class RequestAuthorizationFilter extends GenericFilterBean {

	@Autowired
	private TokenRepository tokens;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String authorization = req.getHeader("Authorization");
		
		if(authorization != null && !authorization.isEmpty()) {
			
			Optional<Token> token = tokens.findById(authorization);
			
			if(token.isPresent() && !token.get().isExpired()) {
			    chain.doFilter(request, response);
			}else {
			    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
			}
		}else {
		    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
		}
	}

}

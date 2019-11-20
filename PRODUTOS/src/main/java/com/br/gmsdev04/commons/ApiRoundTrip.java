package com.br.gmsdev04.commons;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data()
@JsonInclude(Include.NON_NULL)
public class ApiRoundTrip<T> {
	
	//ATRIBUTOS
	private String message;
	@Valid()
	@NotNull(message="objeto data não pode ser vazio.")
	private T data;
	
	//CONSTRUTORES
	public ApiRoundTrip(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	public ApiRoundTrip(String message) {
		super();
		this.message = message;
	}	

}

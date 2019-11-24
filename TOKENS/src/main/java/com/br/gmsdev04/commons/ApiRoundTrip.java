package com.br.gmsdev04.commons;

public class ApiRoundTrip {
	
	//ATRIBUTOS
	private String message;
	private Object data;
	
	//CONSTRUTORES
	public ApiRoundTrip(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	public ApiRoundTrip(String message) {
		super();
		this.message = message;
	}	
	
	//GETTERS AND SETTERS
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

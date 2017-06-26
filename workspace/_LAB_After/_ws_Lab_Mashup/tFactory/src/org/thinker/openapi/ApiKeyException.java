package org.thinker.openapi;

public class ApiKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiKeyException(String msg){
		super(msg);
	}
	
	
	public ApiKeyException(Exception e){
		super(e);
	}
	
}

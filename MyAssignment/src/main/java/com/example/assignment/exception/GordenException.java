package com.example.assignment.exception;

public class GordenException extends RuntimeException {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3221450538329337438L;

	public GordenException() {
		
	}
	
	public GordenException(String str) {
		super(str);
	}
	
	public GordenException(Throwable e) {
		super(e);
	}

}

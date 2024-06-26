package com.zensar.springBoot.exception;

public class InvalidTokenException extends RuntimeException {

	
	private String message;
    public InvalidTokenException() {
    	this.message="";
    }
	public InvalidTokenException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "StockNotFoundException [message=" + message + "]";
	}
}

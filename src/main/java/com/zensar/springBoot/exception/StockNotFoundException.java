package com.zensar.springBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockNotFoundException extends RuntimeException{

	private String message;
    public StockNotFoundException() {
    	this.message="";
    }
	public StockNotFoundException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String toString() {
		return "StockNotFoundException [message=" + message + "]";
	}
	
	
}

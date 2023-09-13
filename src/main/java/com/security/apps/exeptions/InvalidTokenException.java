package com.security.apps.exeptions;

public class InvalidTokenException extends Exception{
	public InvalidTokenException(String message) {
		super(message);
	}
}

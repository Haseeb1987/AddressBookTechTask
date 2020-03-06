package com.addressbook.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhoneNumerValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PhoneNumerValidationException (String message) {
		super(message);
	}
}

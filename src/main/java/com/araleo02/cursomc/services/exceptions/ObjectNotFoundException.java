package com.araleo02.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String e) {
		super(e);
	}

	public ObjectNotFoundException(String e, Throwable cause) {
		super(e, cause);
	}

}

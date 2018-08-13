package com.araleo02.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String e) {
		super(e);
	}

	public DataIntegrityException(String e, Throwable cause) {
		super(e, cause);
	}

}

// aula 84. Tratando exceções adequadamente


package com.araleo02.cursomc.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String e) {
		super(e);
	}

	public FileException(String e, Throwable cause) {
		super(e, cause);
	}

}

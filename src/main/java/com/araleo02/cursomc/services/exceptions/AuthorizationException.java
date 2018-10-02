// aula 72. Restrição de conteúdo: cliente só recupera ele mesmo

package com.araleo02.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String e) {
		super(e);
	}

	public AuthorizationException(String e, Throwable cause) {
		super(e, cause);
	}

}

package com.araleo02.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path); //aula 95. Padronizando formato das exceções
		;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void adderror(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));

	}

}

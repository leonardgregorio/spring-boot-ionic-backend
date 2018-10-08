package com.araleo02.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.araleo02.cursomc.services.exceptions.AuthorizationException;
import com.araleo02.cursomc.services.exceptions.DataIntegrityException;
import com.araleo02.cursomc.services.exceptions.FileException;
import com.araleo02.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegraty(DataIntegrityException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Data Integrity", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Validation error", e.getMessage(), request.getRequestURI());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.adderror(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	// aula 72. Restrição de conteúdo: cliente só recupera ele mesmo
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Access denied",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	// aula 84. Tratando exceções adequadamente
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "File error",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError err = new StandardError(System.currentTimeMillis(), code.value(), "Amazon service error",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(code).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Amazon client error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Amazon S3 error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}

/*
 * @ExceptionHandler(ObjectNotFoundException.class) public
 * ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,
 * HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
 * 
 * }
 * 
 * @ExceptionHandler(DataIntegrityException.class) public
 * ResponseEntity<StandardError> DataIntegraty(DataIntegrityException e,
 * HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
 * 
 * @ExceptionHandler(MethodArgumentNotValidException.class) public
 * ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,
 * HttpServletRequest request) {
 * 
 * ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
 * "Erro de validação!", System.currentTimeMillis());
 * 
 * for (FieldError x : e.getBindingResult().getFieldErrors()) {
 * err.adderror(x.getField(), x.getDefaultMessage()); }
 * 
 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
 * 
 * // aula 72. Restrição de conteúdo: cliente só recupera ele mesmo
 * 
 * @ExceptionHandler(AuthorizationException.class) public
 * ResponseEntity<StandardError> authorization(AuthorizationException e,
 * HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.FORBIDDEN).body(err); }
 * 
 * // aula 84. Tratando exceções adequadamente
 * 
 * @ExceptionHandler(FileException.class) public ResponseEntity<StandardError>
 * file(FileException e, HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
 * 
 * @ExceptionHandler(AmazonServiceException.class) public
 * ResponseEntity<StandardError> amazonService(AmazonServiceException e,
 * HttpServletRequest request) { HttpStatus code =
 * HttpStatus.valueOf(e.getErrorCode()); StandardError err = new
 * StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
 * return ResponseEntity.status(code).body(err); }
 * 
 * @ExceptionHandler(AmazonClientException.class) public
 * ResponseEntity<StandardError> amazonClient(AmazonClientException e,
 * HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
 * 
 * @ExceptionHandler(AmazonS3Exception.class) public
 * ResponseEntity<StandardError> amazonS3(AmazonS3Exception e,
 * HttpServletRequest request) {
 * 
 * StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),
 * e.getMessage(), System.currentTimeMillis()); return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); }
 */

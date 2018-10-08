//aula 95. Padronizando formato das exceções

package com.araleo02.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError(Long timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

/*
 * public StandardError() {
 * 
 * }
 * 
 * 
 * public StandardError(Integer status, String msg, Long timeStamp) { super();
 * this.status = status; this.msg = msg; this.timeStamp = timeStamp; }
 * 
 * 
 * 
 * public Integer getStatus() { return status; }
 * 
 * public void setStatus(Integer status) { this.status = status; }
 * 
 * public String getMsg() { return msg; }
 * 
 * public void setMsg(String msg) { this.msg = msg; }
 * 
 * public Long getTimeStamp() { return timeStamp; }
 * 
 * public void setTimeStamp(Long timeStamp) { this.timeStamp = timeStamp; }
 */

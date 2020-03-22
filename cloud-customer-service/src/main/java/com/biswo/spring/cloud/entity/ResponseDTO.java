package com.biswo.spring.cloud.entity;

public class ResponseDTO<T> {
	
	private String status;
    private String message = "Success!";
    private T body;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public ResponseDTO(String status, String message) {
		super();
		this.status = status;
		this.message = message;
		
	}
	public ResponseDTO() {
		super();
	}
    
    
    

}

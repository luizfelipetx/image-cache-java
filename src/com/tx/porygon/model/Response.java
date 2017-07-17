package com.tx.porygon.model;

import java.io.Serializable;

public class Response  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int code ;
	public String message;
	public String url;
	public Response() {
		
	}
	
	
	public Response(int code, String message, String url) {
		super();
		this.code = code;
		this.message = message;
		this.url = url;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

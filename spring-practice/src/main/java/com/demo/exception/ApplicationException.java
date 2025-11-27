package com.demo.exception;


public class ApplicationException extends RuntimeException {

	private String msg;
	public ApplicationException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		this.msg = msg;
	}
}

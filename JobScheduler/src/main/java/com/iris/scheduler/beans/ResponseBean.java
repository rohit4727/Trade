package com.iris.scheduler.beans;

public class ResponseBean {

	private String statuscode;
	private String message;

	public ResponseBean() {
		super();

	}

	public ResponseBean(String statuscode, String message) {
		super();
		this.statuscode = statuscode;
		this.message = message;
	}

	public String getId() {
		return statuscode;
	}

	public void setId(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

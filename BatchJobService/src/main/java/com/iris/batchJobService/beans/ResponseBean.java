package com.iris.batchJobService.beans;

public class ResponseBean {

	private String statuscode;
	private String message;

	public ResponseBean() {
	}

	public ResponseBean(String statuscode, String message) {
		this.statuscode = statuscode;
		this.message = message;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

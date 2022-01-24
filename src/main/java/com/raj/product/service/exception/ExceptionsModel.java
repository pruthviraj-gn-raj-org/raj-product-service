package com.raj.product.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

public class ExceptionsModel {

	String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSS" , timezone="Asia/Kolkata")
	Date timestamp;
	HttpStatus error;
	int statusCode;
	String path;
	String details;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getError() {
		return error;
	}
	public void setError(HttpStatus error) {
		this.error = error;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ExceptionsModel [message=" + message + ", timestamp=" + timestamp + ", error=" + error + ", statusCode="
				+ statusCode + ", path=" + path + ", details=" + details + ", getMessage()=" + getMessage()
				+ ", getTimestamp()=" + getTimestamp() + ", getError()=" + getError() + ", getStatusCode()="
				+ getStatusCode() + ", getPath()=" + getPath() + ", getDetails()=" + getDetails() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public ExceptionsModel(String message, Date timestamp, HttpStatus error, int statusCode, String path,
			String details) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.error = error;
		this.statusCode = statusCode;
		this.path = path;
		this.details = details;
	}
	
	
}

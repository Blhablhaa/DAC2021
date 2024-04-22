package com.app.custom_exc;

@SuppressWarnings("serial")
public class InvalidStudentDetailsException extends RuntimeException {
	public InvalidStudentDetailsException(String mesg) {
		super(mesg);
	}

}

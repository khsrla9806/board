package com.board.boardproject.common.exception;

public class BadRequestException extends RuntimeException {
	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
	}
}

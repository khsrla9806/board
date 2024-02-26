package com.board.boardproject.common.exception;


public class NotFoundException extends RuntimeException {
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
	}
}

package com.board.boardproject.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	NOT_FOUND("요청하신 리소스를 찾을 수 없습니다.");
	
	private final String message;
}

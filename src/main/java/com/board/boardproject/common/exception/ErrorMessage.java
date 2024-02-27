package com.board.boardproject.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	NOT_FOUND("요청하신 리소스를 찾을 수 없습니다."), 
	DUPLICATED_MEMBER_NICKNAME("이미 존재하는 닉네임입니다."),
	FAIL_PASSWORD_RECHECK("입력한 비밀번호 2개가 다릅니다."),
	MISMATCH_PASSWORD("비밀번호가 일치하지 않습니다.")
	;
	
	private final String message;
}

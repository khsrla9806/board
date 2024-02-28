package com.board.boardproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
	private String nickname;
	private String firstPassword;
	private String secondPassword;
	private String address;
	private String email;
}

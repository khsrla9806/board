package com.board.boardproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequestDto {
	private Long memberId;
	private String nickname;
	private String email;
	private String address;
}

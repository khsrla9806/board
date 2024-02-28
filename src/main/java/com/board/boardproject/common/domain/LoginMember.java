package com.board.boardproject.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 세션에 있는 로그인 정보를 담을 객체
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMember {
	private Long id;
	private String nickname;
}

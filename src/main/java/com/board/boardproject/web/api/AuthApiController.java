package com.board.boardproject.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.boardproject.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthApiController {

	private final MemberService memberService;

	/**
	 * 존재하는 닉네임인지 확인하기 위해 요청
	 */
	@GetMapping("/api/v1/auth/nickname")
	public String findMemberByNickname(String value) {
		return memberService.checkExistMemberByNickname(value) ? "fail" : "pass";
	}

	/**
     * 존재하는 이메일인지 확인하기 위해 요청
     */
	@GetMapping("/api/v1/auth/email")
	public String findMemberByEmail(@RequestParam String value) {
		return memberService.checkExistMemberByEmail(value) ? "fail" : "pass";
	}
}

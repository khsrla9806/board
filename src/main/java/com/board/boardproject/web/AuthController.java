package com.board.boardproject.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.MemberRepository;
import com.board.boardproject.service.MemberService;
import com.board.boardproject.web.dto.SignInRequestDto;
import com.board.boardproject.web.dto.SignUpRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {
	private final MemberService memberService;
	
	@GetMapping("/auth/sign-up-form")
	public String getSignUpForm() {
		return "pages/auth/sign-up";
	}
	
	@GetMapping("/auth/sign-in-form")
	public String getSignInForm() {
		return "pages/auth/sign-in";
	}
	
	@PostMapping("/auth/sign-up")
	public String signUp(SignUpRequestDto dto) {
		
		memberService.signUp(dto);
		
		return "redirect:/auth/sign-in-form";
	}
	
	/**
	 * 로그인 : 세션을 만들어서 등록
	 */
	@PostMapping("/auth/sign-in")
	public String signIn(HttpServletRequest request, SignInRequestDto dto) {
		
		Member member = memberService.signIn(dto);
		
		// 세션 만들기 (Session 있으면 그대로 가져오고, 없으면 새로 생성해서 리턴
		HttpSession session = request.getSession();
		
		LoginMember loginMember = new LoginMember(member.getId(), member.getNickname());
		
		session.setAttribute("loginMember", loginMember);
		// 만료 시간 600초 = 10분
		session.setMaxInactiveInterval(600);
		
		return "redirect:/home";
	}
	
	/**
	 * 로그아웃 : 세션을 만료시킴
	 */
	@PostMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/home";
	}
	
}

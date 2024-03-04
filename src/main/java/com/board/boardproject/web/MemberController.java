package com.board.boardproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.entity.Member;
import com.board.boardproject.service.MemberService;
import com.board.boardproject.web.dto.ProfileUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/member/profile-update-form")
	public String getProfileUpdateForm(LoginMember loginMember, Model model) {
		
		Member member = memberService.getMember(loginMember.getId());
		
		model.addAttribute("member", member);
		
		return "pages/member/profile-update";
	}
	
	@PostMapping("/member/profile-update")
	public String updateProfile(LoginMember loginMember, ProfileUpdateRequestDto dto, HttpSession session) {
		
		Member member = memberService.updateProfile(loginMember, dto);
		
		// 세션을 다시 갱신한다. (변경 내용이 바로 반영될 수 있도록)
		session.setAttribute("loginMember", new LoginMember(member.getId(), member.getNickname()));
		// 만료 시간 600초 = 10분
		session.setMaxInactiveInterval(600);
		
		return "redirect:/home";
	}
}

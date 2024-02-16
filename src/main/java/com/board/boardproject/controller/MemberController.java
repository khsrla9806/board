package com.board.boardproject.controller;

import com.board.boardproject.entity.Member;
import com.board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members/{memberId}")
    public Member getMember(@PathVariable Long memberId) {
        return memberService.getMember(memberId);
    }
}

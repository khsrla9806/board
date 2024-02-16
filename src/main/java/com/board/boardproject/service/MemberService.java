package com.board.boardproject.service;

import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public Member getMember(Long id) {
        return memberMapper.findById(id);
    }
}

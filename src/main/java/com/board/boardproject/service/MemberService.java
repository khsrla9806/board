package com.board.boardproject.service;

import com.board.boardproject.common.exception.ErrorMessage;
import com.board.boardproject.common.exception.NotFoundException;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberMapper;

    public Member getMember(Long id) {
        return memberMapper.findById(id)
        		.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }
}

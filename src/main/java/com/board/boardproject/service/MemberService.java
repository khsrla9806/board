package com.board.boardproject.service;

import com.board.boardproject.common.exception.BadRequestException;
import com.board.boardproject.common.exception.ErrorMessage;
import com.board.boardproject.common.exception.NotFoundException;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.MemberRepository;
import com.board.boardproject.web.dto.SignInRequestDto;
import com.board.boardproject.web.dto.SignUpRequestDto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMember(Long id) {
        return memberRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }
    
    /**
     * 회원가입
     */
    public void signUp(SignUpRequestDto dto) {
    	
    	// 1. 이미 존재하는 닉네임인지 확인
    	Optional<Member> optionalMember = memberRepository.findByNickname(dto.getNickname());
    	
    	if (optionalMember.isPresent()) {
    		throw new BadRequestException(ErrorMessage.DUPLICATED_MEMBER_NICKNAME);
    	}
    	
    	// 2. 비밀번호와 비밀번호 확인이 동일한지 확인
    	if (!dto.getFirstPassword().equals(dto.getSecondPassword())) {
    		throw new BadRequestException(ErrorMessage.FAIL_PASSWORD_RECHECK);
    	}
    	
    	// 3. Member 데이터를 생성
    	Member member = Member.builder()
    			.nickname(dto.getNickname())
    			.password(dto.getFirstPassword())
    			.address(dto.getAddress())
    			.email(dto.getEmail())
    			.createdDate(LocalDateTime.now())
    			.modifiedDate(LocalDateTime.now())
    			.build();
    	
    	memberRepository.save(member);
    }
    
    /**
     * 로그인
     */
    public Member signIn(SignInRequestDto dto) {
    	Member member = memberRepository.findByNickname(dto.getNickname())
    			.orElseThrow(() -> new BadRequestException(ErrorMessage.NOT_FOUND));
    	
    	if (!member.isMatchPassword(dto.getPassword())) {
    		throw new BadRequestException(ErrorMessage.MISMATCH_PASSWORD);
    	}
    	
    	return member;
    }
}

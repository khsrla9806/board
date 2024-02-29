package com.board.boardproject.repository;

import com.board.boardproject.entity.Member;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
	/**
	 * 사용자 단건 조회
	 */
    Optional<Member> findById(Long id);
    
    /**
     * 사용자 닉네임으로 조회
     */
    Optional<Member> findByNickname(String nickname);
    
    /**
     * 사용자 생성
     */
    void save(Member member);
    
    /**
     * 사용자 이메일로 조회
     */
    Optional<Member> findByEmail(String email);
}

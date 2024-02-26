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
}

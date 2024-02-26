package com.board.boardproject.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.board.boardproject.entity.Board;

@Mapper
public interface BoardRepository {
	/**
	 * 게시글 목록 조회
	 * TODO: 페이징으로 변환
	 */
	List<Board> findAll();
	
	/**
	 * 게시글 단건 조회
	 */
	Optional<Board> findById(Long id);
	
	/**
	 * 게시글 저장
	 */
	void save(Board board);
	
	/**
	 * 게시글 수정
	 */
	void update(Board board);
	
	/**
	 * 게시글 삭제
	 */
	void deleteById(Long id);
}

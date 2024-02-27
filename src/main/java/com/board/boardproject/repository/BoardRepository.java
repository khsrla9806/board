package com.board.boardproject.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import com.board.boardproject.entity.Board;
import com.board.boardproject.web.dto.BoardGetRequestDto;

@Mapper
public interface BoardRepository {
	
	/**
	 * 게시글 페이징 조회
	 */
	List<Board> findAll(BoardGetRequestDto dto);
	
	/**
	 * 페이징 총 카운트
	 */
	int getTotalElementCount(String keyword);
	
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

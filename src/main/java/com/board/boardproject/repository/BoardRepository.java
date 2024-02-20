package com.board.boardproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.boardproject.entity.Board;

@Mapper
public interface BoardRepository {
	List<Board> findAll();
	Board findById(Long id);
	void save(Board board);
}

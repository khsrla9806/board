package com.board.boardproject.repository;

import org.apache.ibatis.annotations.Mapper;

import com.board.boardproject.entity.Board;

@Mapper
public interface BoardMapper {
	Board findById(Long id);
}

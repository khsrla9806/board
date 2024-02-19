package com.board.boardproject.service;

import org.springframework.stereotype.Service;

import com.board.boardproject.entity.Board;
import com.board.boardproject.repository.BoardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardMapper boardMapper;
	
	public Board getBoard(Long id) {
		Board board = boardMapper.findById(id);
		
		if (board == null) {
			throw new RuntimeException("NOT FOUND");
		}
		
		return board;
	}
}

package com.board.boardproject.service;

import org.springframework.stereotype.Service;

import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.BoardMapper;
import com.board.boardproject.web.dto.CreateBoardRequestDto;

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
	
	/**
	 * 게시글 생성
	 */
	public void createBoard(CreateBoardRequestDto dto, Member member) {
		Board board = dto.toEntity(member);
		boardMapper.save(board);
	}
}

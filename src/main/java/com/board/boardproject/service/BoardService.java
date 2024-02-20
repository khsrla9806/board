package com.board.boardproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.BoardRepository;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.CreateBoardRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public Board getBoard(Long id) {
		Board board = boardRepository.findById(id);
		
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
		boardRepository.save(board);
	}
	
	/**
	 * 게시글 목록 조회
	 */
	public List<BoardResponseDto> getBoards() {
		List<Board> boards = boardRepository.findAll();
		
		return boards.stream().map(BoardResponseDto::fromEntity)
				.collect(Collectors.toList());
	}
}

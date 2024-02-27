package com.board.boardproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.common.exception.ErrorMessage;
import com.board.boardproject.common.exception.NotFoundException;
import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.BoardRepository;
import com.board.boardproject.web.dto.BoardDetailResponseDto;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.BoardCreateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	/**
	 * 게시글 생성
	 */
	public void createBoard(BoardCreateRequestDto dto, Member member) {
		Board board = dto.toEntity(member);
		boardRepository.save(board);
	}
	
	/**
	 * 게시글 페이징 조회
	 */
	public Pagination<BoardResponseDto> getBoards(Pageable pageable) {
		List<BoardResponseDto> boards = boardRepository.findAll(pageable).stream()
				.map(BoardResponseDto::fromEntity)
				.collect(Collectors.toList());
		
		int elementCount = boardRepository.getTotalElementCount();
		
		return Pagination.of(boards, pageable, elementCount);
	}
	
	
	/**
	 * 게시글 상세 조회
	 */
	public BoardDetailResponseDto getBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		return BoardDetailResponseDto.fromEntity(board);
	}
}

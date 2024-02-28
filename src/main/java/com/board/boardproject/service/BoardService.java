package com.board.boardproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.common.exception.BadRequestException;
import com.board.boardproject.common.exception.ErrorMessage;
import com.board.boardproject.common.exception.NotFoundException;
import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;
import com.board.boardproject.repository.BoardRepository;
import com.board.boardproject.web.dto.BoardDetailResponseDto;
import com.board.boardproject.web.dto.BoardGetRequestDto;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.BoardUpdateRequestDto;
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
	 * 게시글 수정
	 */
	public void updateBoard(BoardUpdateRequestDto dto, LoginMember loginMember) {
		Board board = boardRepository.findById(dto.getBoardId())
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		if (!board.isOwner(loginMember.getId())) {
			throw new BadRequestException("수정 권한이 없습니다.");
		}
		
		board.update(dto.getTitle(), dto.getContent());
		boardRepository.update(board);
	}
	
	/**
	 * 게시글 삭제
	 */
	public void deleteBoard(Long boardId, LoginMember loginMember) {
		
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		if (!board.isOwner(loginMember.getId())) {
			throw new BadRequestException("삭제 권한이 없습니다.");
		}
		
		boardRepository.deleteById(boardId);
	}
	
	/**
	 * 게시글 페이징 조회
	 */
	public Pagination<BoardResponseDto> getBoards(BoardGetRequestDto dto) {
		List<BoardResponseDto> boards = boardRepository.findAll(dto).stream()
				.map(BoardResponseDto::fromEntity)
				.collect(Collectors.toList());
		
		int elementCount = boardRepository.getTotalElementCount(dto.getKeyword());
		
		return Pagination.of(boards, dto.getPageable(), elementCount);
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

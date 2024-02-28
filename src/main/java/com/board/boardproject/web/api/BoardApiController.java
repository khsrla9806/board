package com.board.boardproject.web.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.service.BoardService;
import com.board.boardproject.web.dto.BoardGetRequestDto;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	
	/**
	 * 게시글 페이징 조회
	 */
	@GetMapping("/api/v1/boards")
	public Pagination<BoardResponseDto> getBoards(
			@RequestParam(required = false) String keyword,
			@PageableDefault(size = 5) Pageable pageable
	) {
		BoardGetRequestDto dto = new BoardGetRequestDto(keyword, pageable);
		
		return boardService.getBoards(dto);
	}
	
	/**
	 * 내가 작성한 최근 게시글 조회
	 */
	@GetMapping("/api/v1/boards/my")
	public List<BoardResponseDto> getCurrentMyBoards(
			@RequestParam(defaultValue = "5") Integer size,
			LoginMember loginMember
	) {
		return boardService.getMyCurrentBoards(size, loginMember);
	}
	
	/**
	 * 게시글 삭제
	 */
	@DeleteMapping("/api/v1/boards/{boardId}")
	public void deleteBoard(@PathVariable Long boardId, LoginMember loginMember) {
		boardService.deleteBoard(boardId, loginMember);
	}
	
	/**
	 * 게시글 수정
	 */
	@PutMapping("/api/v1/boards/{boardId}")
	public void updateBoard(@PathVariable Long boardId, BoardUpdateRequestDto dto, LoginMember loginMember) {
		boardService.updateBoard(dto, loginMember);
	}
}

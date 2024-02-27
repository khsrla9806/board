package com.board.boardproject.web.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.service.BoardService;
import com.board.boardproject.web.dto.BoardResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	
	@GetMapping("/api/v1/boards")
	public Pagination<BoardResponseDto> getBoards(@PageableDefault(size = 5) Pageable pageable) {
		return boardService.getBoards(pageable);
	}
}

package com.board.boardproject.web.api;

import java.util.List;

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
	public List<BoardResponseDto> getBoards() {
		return boardService.getBoards();
	}
	
	@GetMapping("/api/v1/boards/page")
	public Pagination<BoardResponseDto> getBoardsPage(@PageableDefault(size = 5) Pageable pageable) {
		return boardService.getBoardsPage(pageable);
	}
}

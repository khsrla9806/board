package com.board.boardproject.web.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

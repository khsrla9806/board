package com.board.boardproject.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.board.boardproject.entity.Board;
import com.board.boardproject.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	
	@GetMapping("/api/v1/boards/{boardId}")
	public Board getBoard(@PathVariable Long boardId) {
		return boardService.getBoard(boardId);
	}
}

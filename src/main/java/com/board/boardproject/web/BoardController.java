package com.board.boardproject.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.boardproject.entity.Member;
import com.board.boardproject.service.BoardService;
import com.board.boardproject.web.dto.CreateBoardRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/create-form")
	public String getCreateForm() {
		return "pages/board-create";
	}
	
	/**
	 * 게시글 업로드
	 */
	@PostMapping("/board")
	public String createPost(CreateBoardRequestDto dto) {
		
		// TODO: 임시 사용자 객체
		Member member = Member.builder().id(1L).build();
		
		boardService.createBoard(dto, member);
		
		return "redirect:/home";
	}
}

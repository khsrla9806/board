package com.board.boardproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.boardproject.web.dto.CreateBoardRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	@GetMapping("/board/create-form")
	public String getCreateForm() {
		return "pages/board-create";
	}
	
	/**
	 * 게시글 업로드
	 */
	@PostMapping("/board")
	public String createPost(CreateBoardRequestDto dto) {
		
		log.info("title = {}, content = {}", dto.getTitle(), dto.getContent());
		
		return "redirect:/home";
	}
}

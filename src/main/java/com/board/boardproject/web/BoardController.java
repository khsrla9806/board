package com.board.boardproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.boardproject.entity.Member;
import com.board.boardproject.service.BoardService;
import com.board.boardproject.web.dto.BoardDetailResponseDto;
import com.board.boardproject.web.dto.BoardUpdateRequestDto;
import com.board.boardproject.web.dto.BoardCreateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	/**
	 * 게시글 업로드 페이지 뷰
	 */
	@GetMapping("/board/create-form")
	public String getCreateForm() {
		return "pages/board-create";
	}
	
	/**
	 * 게시글 업로드
	 */
	@PostMapping("/create-board")
	public String createBoard(BoardCreateRequestDto dto) {
		
		// TODO: 임시 사용자 객체
		Member member = Member.builder().id(1L).build();
		
		boardService.createBoard(dto, member);
		
		return "redirect:/home";
	}
	
	/**
	 * 게시글 수정 페이지 뷰
	 */
	@GetMapping("/board/update-form/{boardId}")
	public String getUpdateForm(@PathVariable Long boardId, Model model) {
		
		model.addAttribute("board", boardService.getBoard(boardId));
		
		return "pages/board-update";
	}
	
	/**
	 * 게시글 수정
	 */
	@PostMapping("/update-board")
	public String updateBoard(BoardUpdateRequestDto dto) {
		
		boardService.updateBoard(dto);
		
		return "redirect:/board/" + dto.getBoardId();
	}
	
	
	/**
	 * 개시글 상세 페이지 뷰
	 */
	@GetMapping("/board/{boardId}")
	public String getBoard(@PathVariable Long boardId, Model model) {
		BoardDetailResponseDto boardDetailDto = boardService.getBoard(boardId);
		
		model.addAttribute("boardDetail", boardDetailDto);
		
		return "pages/board-detail";
	}
}

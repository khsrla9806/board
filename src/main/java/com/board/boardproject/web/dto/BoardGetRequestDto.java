package com.board.boardproject.web.dto;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGetRequestDto {
	private String keyword;
	private Pageable pageable;
}

package com.board.boardproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private Long id;
	private String title;
	private String content;
	private Member member;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		this.modifiedDate = LocalDateTime.now();
	}
}

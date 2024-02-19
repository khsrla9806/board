package com.board.boardproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private Long id;
	private String title;
	private String content;
	private Member writer;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}

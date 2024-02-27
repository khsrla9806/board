package com.board.boardproject.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.board.boardproject.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDto {
	private Long id;
	private String title;
	private String content;
	private String createdDate;
	private String writerNickname;
	
	public static BoardResponseDto fromEntity(Board board) {
		return BoardResponseDto.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.createdDate(getFormattedCreatedDate(board.getCreatedDate()))
				.writerNickname(board.getMember().getNickname())
				.build();
	}
	
	public static String getFormattedCreatedDate(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
	}
}

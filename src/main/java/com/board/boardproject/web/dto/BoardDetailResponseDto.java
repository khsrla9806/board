package com.board.boardproject.web.dto;

import java.time.LocalDateTime;

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
public class BoardDetailResponseDto {
	private Long id;
	private String title;
	private String content;
	private Long memberId;
	private String memberNickname;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public static BoardDetailResponseDto fromEntity(Board board) {
		return BoardDetailResponseDto.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.memberId(board.getMember().getId())
				.memberNickname(board.getMember().getNickname())
				.createdDate(board.getCreatedDate())
				.modifiedDate(board.getModifiedDate())
				.build();
	}
}

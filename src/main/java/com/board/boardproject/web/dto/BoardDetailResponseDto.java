package com.board.boardproject.web.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.UploadFile;

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
	private UploadFile attachedFile;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public static BoardDetailResponseDto fromEntity(Board board, UploadFile attachedFile) {
		return BoardDetailResponseDto.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.memberId(board.getMember().getId())
				.attachedFile(attachedFile)
				.memberNickname(board.getMember().getNickname())
				.createdDate(board.getCreatedDate())
				.modifiedDate(board.getModifiedDate())
				.build();
	}
	
	public String getFormattedCreatedDate() {
		return createdDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
	}
	
	public String getFormattedModifiedDate() {
		return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
	}
}

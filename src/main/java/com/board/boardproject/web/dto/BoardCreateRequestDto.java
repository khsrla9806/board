package com.board.boardproject.web.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequestDto {
	private String title;
	private String content;
	
	// 첨부파일
	private MultipartFile attachedFile;
	
	public Board toEntity(Member member) {
		return Board.builder()
				.title(title)
				.content(content)
				.createdDate(LocalDateTime.now())
				.modifiedDate(LocalDateTime.now())
				.member(member)
				.build();
	}
}

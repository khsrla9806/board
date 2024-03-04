package com.board.boardproject.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadFile {
	private Long id;
	private String uploadFileName;
	private String storeFileName;
	private LocalDateTime createdDate;
	
	// 첨부된 게시글
	private Board board;
	
	public UploadFile(String uploadFileName, String storeFileName) {
		this.uploadFileName = uploadFileName;
		this.storeFileName = storeFileName;
	}
}

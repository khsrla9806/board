package com.board.boardproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.UploadFile;

@Mapper
public interface UploadFileRepository {
	
	/**
	 * 첨부파일을 저장
	 */
	public void save(@Param("uploadFile") UploadFile uploadFile);
	
	/**
	 * 게시글에 첨부된 파일 반환 (현재 1대 1 매핑 관계)
	 */
	public UploadFile findByBoard(@Param("board") Board board);
	
	/**
	 * 게시글에 첨부된 파일 삭제
	 */
	public void deleteByBoard(@Param("board") Board board);
}

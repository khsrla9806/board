package com.board.boardproject.entity.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.board.boardproject.entity.UploadFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FileStore {
	
	private final String fileDir;
	
	public FileStore(@Value("${file.dir}") String fileDir) {
		this.fileDir = fileDir;
	}
	
	/**
	 * @return 파일을 저장할 전체 경로를 반환
	 */
	public String getFullPath(String fileName) {
		return fileDir + fileName;
	}
	
	/**
	 * 단일 파일 업로드
	 * @return 저장된 파일을 UploadFile 형태로 반환 (Null 방지를 위해 Optional 이용)
	 */
	public Optional<UploadFile> storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()) {
			return Optional.empty();
		}
		
		String originalFileName = multipartFile.getOriginalFilename();
		String storeFileName = createStoreFileName(originalFileName);
		
		// 파일을 지정된 경로에 저장
		multipartFile.transferTo(new File(getFullPath(storeFileName)));
		
		return Optional.of(new UploadFile(originalFileName, storeFileName));
	}
	
	/**
	 * 다중 파일 업로드
	 * @return 저장된 파일들을 List<UploadFile> 형태로 반환
	 */
	public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		
		List<UploadFile> storedFiles = new ArrayList<UploadFile>();
		
		for (MultipartFile file : multipartFiles) {
			if (!file.isEmpty()) {
				storedFiles.add(storeFile(file).get());
			}
		}
		
		return storedFiles;
	}
	
	
	private String createStoreFileName(String originalFileName) {
		String extension = extractExtenstion(originalFileName);
		String uuid = UUID.randomUUID().toString();
		
		return uuid + "." + extension;
	}
	
	private String extractExtenstion(String originalFileName) {
		int dot = originalFileName.lastIndexOf('.');
		
		return originalFileName.substring(dot + 1);
	}
}

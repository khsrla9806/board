package com.board.boardproject.web.api;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.entity.UploadFile;
import com.board.boardproject.entity.domain.FileStore;
import com.board.boardproject.service.BoardService;
import com.board.boardproject.web.dto.BoardGetRequestDto;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpHeaders.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	private final FileStore fileStore;
	
	/**
	 * 게시글 페이징 조회
	 */
	@GetMapping("/api/v1/boards")
	public Pagination<BoardResponseDto> getBoards(
			@RequestParam(required = false) String keyword,
			@PageableDefault(size = 5) Pageable pageable
	) {
		BoardGetRequestDto dto = new BoardGetRequestDto(keyword, pageable);
		
		return boardService.getBoards(dto);
	}
	
	/**
	 * 내가 작성한 최근 게시글 조회
	 */
	@GetMapping("/api/v1/boards/my")
	public List<BoardResponseDto> getCurrentMyBoards(
			@RequestParam(defaultValue = "5") Integer size,
			LoginMember loginMember
	) {
		return boardService.getMyCurrentBoards(size, loginMember);
	}
	
	/**
	 * 게시글 삭제
	 */
	@DeleteMapping("/api/v1/boards/{boardId}")
	public void deleteBoard(@PathVariable Long boardId, LoginMember loginMember) {
		boardService.deleteBoard(boardId, loginMember);
	}
	
	/**
	 * 게시글 수정
	 */
	@PutMapping("/api/v1/boards/{boardId}")
	public void updateBoard(@PathVariable Long boardId, BoardUpdateRequestDto dto, LoginMember loginMember) {
		boardService.updateBoard(dto, loginMember);
	}
	
	/**
	 * 게시글 첨부파일 다운로드
	 */
	@GetMapping("/api/v1/boards/attach/{boardId}")
	public ResponseEntity<Resource> downloadAttachedFile(@PathVariable Long boardId) throws MalformedURLException {
		UploadFile attachedFile = boardService.findAttachedFile(boardId);
		
		String fullResourcePath = fileStore.getFullResourcePath(attachedFile.getStoreFileName());
		UrlResource resourece = new UrlResource(fullResourcePath);
		
		// 업로드 파일 이름에 한글이 있을 수 있기 떄문에 인코딩을 한번 해줌
		String encodedUploadFileName = UriUtils.encode(attachedFile.getUploadFileName(), StandardCharsets.UTF_8);
		
		// 파일 다운로드시에 HTTP 헤더 규약을 넣어줘야 한다.
		String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
		
		return ResponseEntity.ok()
				.header(CONTENT_DISPOSITION, contentDisposition)
				.body(resourece);
	}
}

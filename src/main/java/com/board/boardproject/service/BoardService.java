package com.board.boardproject.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.boardproject.common.domain.LoginMember;
import com.board.boardproject.common.domain.Pagination;
import com.board.boardproject.common.exception.BadRequestException;
import com.board.boardproject.common.exception.ErrorMessage;
import com.board.boardproject.common.exception.NotFoundException;
import com.board.boardproject.entity.Board;
import com.board.boardproject.entity.Member;
import com.board.boardproject.entity.UploadFile;
import com.board.boardproject.entity.domain.FileStore;
import com.board.boardproject.repository.BoardRepository;
import com.board.boardproject.repository.UploadFileRepository;
import com.board.boardproject.web.dto.BoardDetailResponseDto;
import com.board.boardproject.web.dto.BoardGetRequestDto;
import com.board.boardproject.web.dto.BoardResponseDto;
import com.board.boardproject.web.dto.BoardUpdateRequestDto;
import com.board.boardproject.web.dto.BoardCreateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final UploadFileRepository uploadFileRepository;
	private final FileStore fileStore;
	
	/**
	 * 게시글 생성
	 */
	@Transactional
	public void createBoard(BoardCreateRequestDto dto, Member member) {
		Board board = dto.toEntity(member);
		boardRepository.save(board);
		
		
		try {
			Optional<UploadFile> uploadFileOptional = fileStore.storeFile(dto.getAttachedFile());
			
			if (uploadFileOptional.isPresent()) {
				
				UploadFile uploadFile = uploadFileOptional.get();
				uploadFile.setBoard(board); // 연관된 Board 의존관계를 형성
				
				// 업로드된 파일의 정보를 DB에 저장
				uploadFileRepository.save(uploadFile);
			}
			
		} catch (IOException e) {
			throw new BadRequestException("잘못된 파일 형식");
		}
		
	}
	
	/**
	 * 게시글 수정
	 */
	public void updateBoard(BoardUpdateRequestDto dto, LoginMember loginMember) {
		Board board = boardRepository.findById(dto.getBoardId())
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		if (!board.isOwner(loginMember.getId())) {
			throw new BadRequestException("수정 권한이 없습니다.");
		}
		
		board.update(dto.getTitle(), dto.getContent());
		boardRepository.update(board);
	}
	
	/**
	 * 게시글 삭제
	 */
	public void deleteBoard(Long boardId, LoginMember loginMember) {
		
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		if (!board.isOwner(loginMember.getId())) {
			throw new BadRequestException("삭제 권한이 없습니다.");
		}
		
		boardRepository.deleteById(boardId);
	}
	
	/**
	 * 게시글 페이징 조회
	 */
	public Pagination<BoardResponseDto> getBoards(BoardGetRequestDto dto) {
		List<BoardResponseDto> boards = boardRepository.findAll(dto).stream()
				.map(BoardResponseDto::fromEntity)
				.collect(Collectors.toList());
		
		int elementCount = boardRepository.getTotalElementCount(dto.getKeyword());
		
		return Pagination.of(boards, dto.getPageable(), elementCount);
	}
	
	/**
	 * 최근 게시글 조회
	 */
	public List<BoardResponseDto> getMyCurrentBoards(Integer size, LoginMember loginMember) {
		List<Board> boards = boardRepository.findByMemberIdWithSize(loginMember.getId(), size);
		
		return boards.stream()
				.map(BoardResponseDto::fromEntity).collect(Collectors.toList());
	}
	
	
	/**
	 * 게시글 상세 조회
	 */
	public BoardDetailResponseDto getBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		UploadFile uploadFile = uploadFileRepository.findByBoard(board);
		
		return BoardDetailResponseDto.fromEntity(board, uploadFile);
	}
	
	/**
	 * 게시글에 첨부된 파일 가져오기
	 */
	public UploadFile findAttachedFile(Long boardId) {
		// 존재하는 게시글인지 확인
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
		
		// 해당 게시글에 파일이 있는지 확인
		UploadFile uploadFile = uploadFileRepository.findByBoard(board);
		
		if (uploadFile == null) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND);
		}
		
		return uploadFile;
	}
}

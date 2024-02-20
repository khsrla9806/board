<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="board-detail">
		<p>게시글 제목</p>
		<div class="board-title">
			${boardDetail.title}
		</div>
		
		<p>게시글 내용</p>
		<div class="board-content">
			${boardDetail.content}
		</div>
		
		<p>작성일시</p>
		<div class="board-created-date row-item">
			${boardDetail.createdDate}
		</div>
		
		<p>수정일시</p>
		<div class="board-modified-date row-item">
			${boardDetail.modifiedDate}
		</div>
		
		<p>작성자</p>
		<div class="board-writer-nickname row-item">
			${boardDetail.memberNickname}
		</div>
		
		<div class="division-line"></div>
		
		<div class="board-detail-bottom-area">
			<div class="move-btn-area">
				<button class="classic-btn" type="button" onClick="#">뒤로 가기</button>
			</div>
			
			<div class="modify-btn-area">
				<button class="classic-btn" type="button" onClick="#">게시글 수정</button>
				<button class="classic-btn" type="button" onClick="#">게시글 삭제</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>
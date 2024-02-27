<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form class="board-create-form" name="board-create-form" method="post" action="/update-board">
		<h1>게시글 수정</h1>
		
		<input id="boardId" name="boardId" type="hidden" value="${board.id}" />
		
		<div>
			<input id="title" name="title" type="text" placeholder="제목을 입력해주세요." value="${board.title}" />
		</div>
		
		<div>
			<textarea id="content" name="content" placeholder="내용을 입력해주세요.">${board.content}</textarea>
		</div>
		
	  	<button id="btn">게시글 수정</button>
	</form> 
	
</div>

<%@ include file="../layout/footer.jsp"%>
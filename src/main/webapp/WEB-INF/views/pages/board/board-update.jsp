<%@page import="com.board.boardproject.web.dto.BoardDetailResponseDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<% 
	BoardDetailResponseDto board = (BoardDetailResponseDto) request.getAttribute("board");
	String content = board.getContent();
	boolean isInvalidContent = content == null || content.isBlank();
%>

<div class="container">

	<form id="boardUpdate" class="board-create-form" name="board-create-form" onSubmit="updateBoard(event)">
		<h1>게시글 수정</h1>
		<br>
		<input id="boardId" name="boardId" type="hidden" value="${board.id}" />
		
		<div>
			<input id="title" name="title" type="text" placeholder="제목을 입력해주세요." value="${board.title}" />
		</div>
		
		<div>
			<textarea id="content" name="content" placeholder="내용을 입력해주세요."></textarea>
		</div>
		<br>
	  	<button id="btn">게시글 수정</button>
	</form> 
	
</div>

<script type="text/javascript">



/**
 * 썸머노트 적용
 */
$(document).ready(function() {
	$('#content').summernote({
		  weigth: 800,
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '내용을 작성해주세요.'	//placeholder 설정
          
	});
});

// 썸머노트 내부에 기존 데이터를 넣는다.
if (!<%=isInvalidContent%>) {
	$('#content').summernote('code', '<%=content%>');
}

</script>

<script src="/js/board-update.js"></script>

<%@ include file="../../layout/footer.jsp"%>
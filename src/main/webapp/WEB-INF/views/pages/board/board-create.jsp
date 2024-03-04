<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<div class="container">

	<form class="board-create-form" name="board-create-form" enctype="multipart/form-data" method="post" action="/create-board">
		<h1>게시글 등록</h1>
		<br>
		<div>
			<input id="title" name="title" type="text" placeholder="제목을 입력해주세요." />
		</div>
		
		<div>
			<textarea id="content" name="content" placeholder="내용을 입력해주세요."></textarea>
		</div>
		
		<div>
			<input id="attachedFile" name="attachedFile" type="file" placeholder="파일을 첨부하세요." />
		</div>
		
		<br>
	  	<button id="btn">게시글 등록</button>
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

</script>

<%@ include file="../../layout/footer.jsp"%>
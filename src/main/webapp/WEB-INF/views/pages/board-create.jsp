<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form name="board-create-form" method="post" action="/board">
		<table border="1">
			<tr>
				<td colspan="2"><h2>게시글 작성</h2></td>
			</tr>
			<tr>
				<td align="center" width="100">제목</td>
				<td width="430"><input name="title" size="100"></td>
			</tr>
			<tr>
				<td align="center" width="100">내용</td>
				<td width="430"><input name="content" size="100"></td>
			</tr>
			<tr>
	  	</table>
	  	<button type="submit">등록</button>
	</form> 
	
</div>

<%@ include file="../layout/footer.jsp"%>
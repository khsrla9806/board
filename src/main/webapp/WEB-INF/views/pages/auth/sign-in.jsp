<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<div class="container">

	<form class="board-create-form" name="board-create-form" method="post" action="/auth/sign-in">
		<h1>로그인</h1>
		<br>
		
		<p>닉네임</p>
		<div>
			<input id="nickname" name="nickname" type="text" placeholder="닉네임을 입력해주세요." />
		</div>
		
		<p>비밀번호</p>
		<div>
			<input id="password" type="password" name="password" placeholder="비밀번호를 입력해주세요." />
		</div>
		
	  	<button id="btn">로그인</button>
	</form> 
	
</div>



<%@ include file="../../layout/footer.jsp"%>
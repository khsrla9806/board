<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<div class="container">

	<form class="board-create-form" name="board-create-form" method="post" action="/auth/sign-up">
		<h1>회원가입</h1>
		<br>
		
		<p>닉네임</p>
		<div>
			<input id="nickname" name="nickname" type="text" placeholder="사용하실 닉네임을 입력해주세요." />
		</div>
		
		<p>비밀번호</p>
		<div>
			<input id="password-first" name="firstPassword" placeholder="사용하실 비밀번호를 입력해주세요." />
		</div>
		
		<p>비밀번호 확인</p>
		<div>
			<input id="password-second" name="secondPassword" placeholder="비밀번호를 확인합니다." />
		</div>
		
	  	<button id="btn">회원가입</button>
	</form> 
	
</div>



<%@ include file="../../layout/footer.jsp"%>
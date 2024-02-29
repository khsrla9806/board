<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<script type="text/javascript">
	
	function goPopup(){
		// 주소 등록 팝업을 호출해서 띄워준다.
		var pop = window.open("/map/popup","pop","width=570,height=420, scrollbars=yes, resizable=yes");
	}
	
	// 호출된 팝업으로부터 입력된 주소 데이터를 콜백받을 함수
	function jusoCallBack(roadFullAddr){
		// 사용자가 입력한 "도로명 주소 + 상세주소"만 필요하기 때문에 이를 사용
		document.form.address.value = roadFullAddr;
	}

</script>

<div class="container">

	<form class="board-create-form" id="form" name="form" method="post" action="/auth/sign-up">
		<h1>회원가입</h1>
		<br>
		
		<p>닉네임</p>
		<div>
			<input id="nickname" name="nickname" type="text" placeholder="사용하실 닉네임을 입력해주세요." />
		</div>
		
		<p>비밀번호</p>
		<div>
			<input id="password-first" type="password" name="firstPassword" placeholder="사용하실 비밀번호를 입력해주세요." />
		</div>
		
		<p>비밀번호 확인</p>
		<div>
			<input id="password-second" type="password" name="secondPassword" placeholder="비밀번호를 확인합니다." />
		</div>
		
		<p>이메일</p>
		<div class="email-box">
			<input id="email" name="email" placeholder="이메일을 입력하세요." />
		</div>
		
		<p>주소</p>
		<div class="address-box">
			<input id="address" name="address" placeholder="주소 검색을 통해 입력해주세요." readonly />
			<button id="address-search-btn" type="button" onclick="goPopup()">주소검색</button>
		</div>
		
	  	<button id="btn">회원가입</button>
	</form> 
	
</div>



<%@ include file="../../layout/footer.jsp"%>
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

	<form class="board-create-form" id="form" name="form" method="post" action="/member/profile-update">
		<h1>회원 정보 수정</h1>
		<br>
		
		<input id="memberId" name="memberId" type="hidden" value="${member.id}" readonly />
		
		<p>닉네임</p>
		<p class="description">변경할 닉네임을 입력해주세요.</p>
		<div>
			<input id="nickname" name="nickname" type="text" placeholder="사용하실 닉네임을 입력해주세요." value="${member.nickname}" />
		</div>
		
		<p>이메일</p>
		<p class="description">이메일을 변경이 불가능합니다.</p>
		<div class="email-box">
			<input id="email" name="email" class="inputDisable" placeholder="이메일을 입력하세요." value="${member.email}" readonly />
		</div>
		
		<p>주소</p>
		<p class="description">변경하고자 하는 주소를 찾아서 입력해주세요.</p>
		<div class="address-box">
			<input id="address" name="address" class="inputDisable" placeholder="주소 검색을 통해 입력해주세요." value="${member.address}" readonly />
			<button id="address-search-btn" type="button" onclick="goPopup()">주소검색</button>
		</div>
		
		<br>
	  	<button id="btn">수정</button>
	</form> 
	
</div>



<%@ include file="../../layout/footer.jsp"%>
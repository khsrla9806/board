<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>

<div class="container">

	<form class="board-create-form" id="form" name="form" method="post" action="/auth/sign-up">
		<h1>회원가입</h1>
		<br>
		
		<p>닉네임</p>
		<div>
			<input id="nickname" name="nickname" type="text" placeholder="사용하실 닉네임을 입력해주세요." />
		</div>
		<p id="nickname-error" class="errorDescription" style="visibility: hidden;"></p>
		
		<p>비밀번호</p>
		<div>
			<input id="password-first" type="password" name="firstPassword" placeholder="사용하실 비밀번호를 입력해주세요." />
		</div>
		<p id="first-password-error" class="errorDescription" style="visibility: hidden;"></p>
		
		<p>비밀번호 확인</p>
		<div>
			<input id="password-second" type="password" name="secondPassword" placeholder="비밀번호를 확인합니다." />
		</div>
		<p id="second-password-error" class="errorDescription" style="visibility: hidden;"></p>
		
		<p>이메일</p>
		<div class="email-box">
			<input id="email" name="email" placeholder="이메일을 입력하세요." />
		</div>
		<p id="email-error" class="errorDescription" style="visibility: hidden;"></p>
		
		<p>주소</p>
		<div class="address-box">
			<input id="address" name="address" placeholder="주소 검색을 통해 입력해주세요." readonly />
			<button id="address-search-btn" type="button" onclick="goPopup()">주소검색</button>
		</div>
		
	  	<button id="btn" disabled>회원가입</button>
	</form> 
	
</div>

<script type="text/javascript">

	// DOM Elements 
	const nicknameBox = document.getElementById("nickname");
	const firstPasswordBox = document.getElementById("password-first");
	const secondPasswordBox = document.getElementById("password-second");
	const emailBox = document.getElementById("email");
	const signUpButton = document.getElementById("btn");
	const addressBox = document.getElementById("address");
	
	// Regex
	const nicknameRegex = /^[a-z0-9]{6,20}$/; // 영어와 숫자 조합의 6에서 20자리 닉네임
	const passwordRegex = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/; // 8~16자 영문, 숫자, 특수문자를 최소 한 개씩 포함
	const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	
	// Validation Check Value
	let passNickname = false, passNicknameDuplicate = false, passFirstPassword = false, passSecondPassword = false, passEmail = false, passAddress = false;

	function goPopup(){
		// 주소 등록 팝업을 호출해서 띄워준다.
		var pop = window.open("/map/popup","pop","width=570,height=420, scrollbars=yes, resizable=yes");
	}
	
	// 호출된 팝업으로부터 입력된 주소 데이터를 콜백받을 함수
	function jusoCallBack(roadFullAddr){
		// 사용자가 입력한 "도로명 주소 + 상세주소"만 필요하기 때문에 이를 사용
		addressBox.value = roadFullAddr;
		passAddress = true;
	}
	
	// 닉네임 유효성 검사
	nicknameBox.addEventListener("input", e => {
		const value = e.target.value;
		
		if (nicknameRegex.test(value)) {
			switchHidden("nickname-error");
			passNickname = true;
		} else {
			document.getElementById("nickname-error").innerText = "닉네임은 영어 소문자와 숫자로 이루어진 6에서 20자리여야 합니다.";
			document.getElementById("nickname-error").style.color = '#FA5858';
			switchVisible("nickname-error");
			passNickname = false;
			passNicknameDuplicate = false;
		}
	});
	
	// 닉네임 중복 검사
	nicknameBox.addEventListener("blur", e => { // focus가 밖으로 벗어났을 때 동작하는 이벤트 (change로 하면 이전 값을 가지고 있어 올바른 검사가 되지 않음)
		
		if (passNickname) {
			const value = e.target.value;
			
			$.ajax({
				type: 'get',
				url: `/api/v1/auth/nickname?value=\${value}` // JSP 파일에서 벡틱(`) 쓸 때, $(달러)가 겹치기 떄문에 escape 문자를 앞에 써줘야 한다.
			}).done(response => {
				console.log("닉네임 체크 결과 : " + response);
				if (response == 'pass') {
					passNicknameDuplicate = true;
					document.getElementById("nickname-error").innerText = "사용할 수 있는 닉네임입니다.";
					document.getElementById("nickname-error").style.color = '#0080FF';
					switchVisible("nickname-error");
				} else if (response == 'fail') {
					passNicknameDuplicate = false;
					document.getElementById("nickname-error").innerText = "중복된 닉네임입니다. 다른 닉네임을 사용해주세요.";
					document.getElementById("nickname-error").style.color = '#FA5858';
					switchVisible("nickname-error");
				}
			}).fail(error => {
				
			});
			
			checkAllPass();
		}
	});

	// 비밀번호 유효성 검사
	firstPasswordBox.addEventListener("input", e => {
		const value = e.target.value;
		
		if (passwordRegex.test(value)) {
			document.getElementById("first-password-error").innerText = "사용할 수 있는 비밀번호입니다.";
			document.getElementById("first-password-error").style.color = '#0080FF';
			switchVisible("first-password-error");
			passSecondPassword = true;
		} else {
			document.getElementById("first-password-error").innerText = "비밀번호는 8에서 16자 영문, 숫자, 특수문자를 최소 1개 포함해야 합니다.";
			document.getElementById("first-password-error").style.color = '#FA5858';
			switchVisible("first-password-error");
			passSecondPassword = false;
		}
		
		checkAllPass();
	});
	
	// 비밀번호 확인 유효성 검사
	secondPasswordBox.addEventListener("input", e => {
		const value = e.target.value;
		
		if (firstPasswordBox.value === value) {
			document.getElementById("second-password-error").innerText = "비밀번호가 일치합니다.";
			document.getElementById("second-password-error").style.color = '#0080FF';
			switchVisible("second-password-error");
			passSecondPassword = true;
		} else {
			document.getElementById("second-password-error").innerText = "비밀번호가 일치하지 않습니다.";
			document.getElementById("second-password-error").style.color = '#FA5858';
			switchVisible("second-password-error");
			passSecondPassword = false;
		}
		
		checkAllPass();
	});
	
	// 이메일 유효성 검사
	emailBox.addEventListener("input", e => {
		const value = e.target.value;
		
		if (emailRegex.test(value)) {
			document.getElementById("email-error").innerText = "사용할 수 있는 이메일입니다.";
			document.getElementById("email-error").style.color = '#0080FF';
			switchVisible("email-error");
			passEmail = true;
		} else {
			document.getElementById("email-error").innerText = "올바르지 않은 이메일 형식입니다.";
			document.getElementById("email-error").style.color = '#FA5858';
			switchVisible("email-error");
			passEmail = false;
		}
		
		checkAllPass();
	});
	
	function activeButton() {
		signUpButton.disabled = false;
	}
	
	function deactiveButton() {
		signUpButton.disabled = true;
	}
	
	function switchVisible(id) {
		document.getElementById(id).style.visibility = "visible";
	}
	
	function switchHidden(id) {
		document.getElementById(id).style.visibility = "hidden";
	}
	
	function checkAllPass() {
		if (passNickname && passNicknameDuplicate && passFirstPassword && passSecondPassword && passEmail && passAddress) {
			signUpButton.disabled = false;
		} else {
			signUpButton.disabled = true;
		}
	}
	
</script>



<%@ include file="../../layout/footer.jsp"%>
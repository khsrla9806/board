<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp"%>

<input type="hidden" id="loginMember" name="loginMember" value="${loginMember}" />

<div class="container">
	<!-- board -->
	<div class="content">
		<!-- board search area -->
		<div id="board-search">
			<div class="search-window">
				<form action="">
					<div class="search-wrap">
						<input id="search" name="search" type="search" placeholder="제목으로 검색하세요." value="">
						<button class="btn">검색</button>
					</div>
				</form>
			</div>
		</div>
		<!-- board list area -->
		<div id="board-list">
			
		</div>
		
		<!-- pagination area -->
		<div id="page_wrap">
			<div id="page_nation" class="page_nation">
			
		   </div>
		</div>
	</div>
	
	<!-- side -->
	<div class="side">
		<div class="user-box">
			<c:if test="${empty loginMember}">
				<div class="user-nickname">
					로그인을 진행해주세요.
				</div>
				
				<button class="classic-btn" type="button" onClick="location.href='/auth/sign-in-form'">로그인</button>
				<br>
				<br>
				<button class="classic-btn" type="button" onClick="location.href='/auth/sign-up-form'">회원가입</button>
			</c:if>
			<c:if test="${! empty loginMember}">
				<div class="user-nickname">
					${loginMember.nickname}님 환경합니다.
				</div>
				
				<button class="classic-btn" type="button" onClick="location.href='/board/create-form'">게시글 작성하기</button>
				<br>
				<br>
				<button class="classic-btn" type="button" onClick="logout()">로그아웃</button>
			</c:if>
		</div>
		<div class="side-box">
			<c:if test="${not empty loginMember}">
				<div class="side-box-title">내가 최근에 작성한 게시글</div>
				<div id="my-current-board-list">
					<!-- 최근 게시글 영역 -->
				</div>
			</c:if>
			<c:if test="${empty loginMember}">
				<div class="side-box-title">
					로그인 후 사용해주세요.
				</div>
			</c:if>
		</div>
	</div>
</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
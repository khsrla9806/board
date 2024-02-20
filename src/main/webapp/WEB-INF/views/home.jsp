<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

<div class="container">
	<!-- board -->
	<div class="content">
		<!-- board seach area -->
		<div id="board-search">
			<div class="search-window">
				<form action="">
					<div class="search-wrap">
						<input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
						<button type="submit" class="btn">검색</button>
					</div>
				</form>
			</div>
		</div>
		<!-- board list area -->
		<div id="board-list">
			
		</div>
	</div>
	
	<!-- side -->
	<div class="side">
		<div class="user-box">
			<div class="user-nickname">
				USER NICKNAME
			</div>
			
			<button class="classic-btn" type="button" onClick="location.href='/board/create-form'">게시글 작성하기</button>
		</div>
		<div class="side-box">SIDE AREA</div>
	</div>
</div>

<script src="/js/board.js"></script>

<%@ include file="./layout/footer.jsp"%>
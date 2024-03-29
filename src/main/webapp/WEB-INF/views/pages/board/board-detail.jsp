<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../layout/header.jsp"%>

<!-- JSP에서 개행문자를 <br>로 바꿔서 처리하기 위한 설정 (가장 위에 작성) -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>

<div class="container">
	<div class="board-detail">
		<p class="small-title">게시글 제목</p>
		<div class="board-title">
			${boardDetail.title}
		</div>
		
		<p class="small-title">게시글 내용</p>
		<div class="board-content">
			${fn:replace(boardDetail.content, replaceChar, "<br>")} <!-- JSP에서는 개행문자가 먹지 않는다. <br>로 변환 -->
		</div>
		
		<p class="small-title">작성일시</p>
		<div class="board-created-date row-item">
			${boardDetail.getFormattedCreatedDate()}
		</div>
		
		<p class="small-title">수정일시</p>
		<div class="board-modified-date row-item">
			${boardDetail.getFormattedModifiedDate()}
		</div>
		
		<p class="small-title">작성자</p>
		<div class="board-writer-nickname row-item">
			${boardDetail.memberNickname}
		</div>
		
		<div class="division-line"></div>
		
		<c:if test='${empty boardDetail.attachedFile}'>
			첨부된 파일이 존재하지 않습니다.
		</c:if>
		
		<c:if test='${not empty boardDetail.attachedFile}'>
			<div>
				첨부 파일 : <a href='/api/v1/boards/attach/${boardDetail.id}'>${boardDetail.attachedFile.uploadFileName}</a>
			</div>
			<div>
				<p style='font-size: 12px; margin-top: 5px; color: #2E64FE;'>파일명을 클릭하여 저장할 수 있습니다.</p>
			</div>
		</c:if>
		
		<div class="division-line"></div>
		
		<div class="board-detail-bottom-area">
			<div class="move-btn-area">
				<button class="classic-btn" type="button" onClick="moveBack()">뒤로 가기</button>
			</div>
			
			<c:if test="${not empty loginMember and loginMember.id eq boardDetail.memberId}">
				<div class="modify-btn-area">
					<button class="classic-btn" type="button" onClick="javascript:location.href='/board/update-form/${boardDetail.id}'">게시글 수정</button>
					<button class="classic-btn" type="button" onClick="deleteBoard(${boardDetail.id})">게시글 삭제</button>
				</div>
			</c:if>
		</div>
	</div>
</div>

<script src="/js/board-detail.js"></script>

<%@ include file="../../layout/footer.jsp"%>
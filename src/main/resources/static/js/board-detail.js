/**
 * 게시글 삭제
 */
function deleteBoard(boardId) {
	
	if (confirm("게시글을 정말 삭제하시겠습니까?") == true) {
		$.ajax({
			type: 'delete',
			url: `/api/v1/boards/${boardId}`
		})
		.done(response => {
			// home 화면으로 Redirect
			location.replace("/home");
		})
		.fail(error => {
			// 삭제 실패 : 홈 화면으로 이동
			location.replace("/home");
		});	
	}
	
	return;
}

let params = new URLSearchParams(location.search);
let page = params.has('page') ? parseInt(params.get('page')) : 0;

/**
 * 뒤로 가기 눌렀을 때, 이전 페이지를 유지 하기 위한 메서드
 */
function moveBack() {
	location.href = `/home?page=${page}`;
}
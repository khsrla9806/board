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
			location.href = '/home';
		})
		.fail(error => {
			// 삭제 실패
		});	
	}
	
	return;
}
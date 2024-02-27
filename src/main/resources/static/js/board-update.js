/**
 * 게시글 수정
 */
function updateBoard(event) {
	// form 태그가 가지고 있는 action 경로를 비활성화 한다.
	// from 태그 안에 버튼이 있기 떄문에 필요하다.
	event.preventDefault();
	
	const boardId = $('#boardId').val();
	const title = $('#title').val();
	const content = $('#content').val();
	
	$.ajax({
		type: 'put',
		url: `/api/v1/boards/${boardId}`,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		data: {
			boardId,
			title,
			content
		}
	})
	.done(response => {
		location.replace(`/board/${boardId}`);
	})
	.fail(error => {
		location.replace('/home');
	});
}
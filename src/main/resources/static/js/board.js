
function getBoards() {
	$.ajax({
		type: 'get',
		url: '/api/v1/boards'
	}).done(response => {
		response.forEach((board) => {
			let boardItem = getBoardItem(board);
			$('#board-list').append(boardItem);
		});
	}).fail(error => {
		console.log(error);
	});
}

getBoards();

function getBoardItem(board) {
	let item = `
			<div class="content-element">
				<div class="board-title" id="board-title-value">
					${board.title}
				</div>
				
				<div class="board-content" id="board-content-value">
					${board.content}
				</div>
				
				<div class="board-created-date">
					생성일시: <span id="board-created-date-value">${board.createdDate}</span>
				</div>
				
				<div class="board-bottom-area">
					<div class="board-writer-nickname">
						작성자: <span id="board-writer-nickanme-value">${board.writerNickname}</span>
					</div>
					<button class="classic-btn">
						게시글 상세보기
					</button>
				</div>
			</div>
	`;
	
	return item;
}
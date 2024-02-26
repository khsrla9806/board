
function getBoards() {
	$.ajax({
		type: 'get',
		url: '/api/v1/boards/page'
	}).done(response => {
		response.data.forEach((board) => {
			let boardItem = getBoardItem(board);
			$('#board-list').append(boardItem);
		});
		
		setPagination(response);
		
	}).fail(error => {
		console.log(error);
	});
}

function getBoardsPage(page) {
	
	// 첫 번째 페이지인 경우 예외처리
	if (page < 0) {
		alert("첫 번째 페이지입니다.");
		return;
	}
	
	// page에 아무것도 들어오지 않은 경우 예외 처리
	if (page == undefined || page < 0) {
		page = 0;
	}
	
	$.ajax({
		type: 'get',
		url: `/api/v1/boards/page?page=${page}`
	}).done(response => {
		
		// 마지막 페이지인 경우 예외처리
		if (page >= response.totalPages) {
			alert("마지막 페이지입니다.");
			return;
		}
		
		$('#board-list').empty();
		response.data.forEach((board) => {
			let boardItem = getBoardItem(board);
			$('#board-list').append(boardItem);
		});
		
		setPagination(page, response);
		
		// 게시글 리스트 상단으로 스크롤 이동
		window.scrollTo({top: 0, behavior: 'smooth'});
		
	}).fail(error => {
		console.log(error);
	});
}

getBoardsPage();

function setPagination(page, response) {
	
	// pagination UI 구성을 위해 필요한 정보들
	const maxSize = 5;
	const startPage = Math.floor(page / maxSize) * maxSize + 1;
	const lastPage = startPage + (maxSize - 1) > response.totalPages ? response.totalPages : startPage + (maxSize - 1);
	
	// 이전 블럭이 존재하지 않는 경우
	if (startPage == 1) {
		document.getElementsByClassName("pprev").style.visibility = 'hidden';
	} else {
		document.getElementsByClassName("pprev").style.visibility = 'visible';
	}
	
	// 이전 페이지가 존재하지 않는 경우
	if (page == 0) {
		document.getElementsByClassName("prev").style.visibility = 'hidden';
	} else {
		document.getElementsByClassName("prev").style.visibility = 'visible';
	}
	
	// 다음 블럭이 존재하는 경우
	if (lastPage >= response.totalPages) {
		document.getElementsByClassName("nnext").style.visibility = 'hidden';
	} else {
		document.getElementsByClassName("nnext").style.visibility = 'visible';
	}
	
	// 다음 페이지가 존재하지 않는 경우
	if (page == response.totalPages - 1) {
		document.getElementsByClassName("next").style.visibility = 'hidden';
	} else {
		document.getElementsByClassName("next").style.visibility = 'visible';
	}
	
	let pagination = `
		<a class="arrow pprev" href="javascript:void(0)" onClick="getBoardsPage(${page - 1})"></a>
      	<a class="arrow prev" href="javascript:void(0)" onClick="getBoardsPage(${page - 1})"></a>
	`;
	
	
	for (let i = startPage; i <= lastPage; i++) {
		
		if (response.pageable.pageNumber + 1 == i) {
			pagination += `
				<a href="javascript:void(0)" onClick="getBoardsPage(${i - 1})" class="active">${i}</a>
			`;	
		} else {
			pagination += `
				<a href="javascript:void(0)" onClick="getBoardsPage(${i - 1})">${i}</a>
			`;	
		}
	}
	
	
	pagination += `
		<a class="arrow next" href="javascript:void(0)" onClick="getBoardsPage(${page + 1})"></a>
		<a class="arrow nnext" href="javascript:void(0)" onClick="getBoardsPage(${page + 1})"></a>
	`;
	
	$('#page_nation').empty().append(pagination);
}

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
					<button type="button" onClick="location.href='/board/${board.id}'" class="classic-btn">
						게시글 상세보기
					</button>
				</div>
			</div>
	`;
	
	return item;
}
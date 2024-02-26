
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

/**
 * board 데이터 응답 형태를 받아서 HTML에 넣어줄 요소를 반환하는 함수
 */
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

/**
 * 페이지네이션 버튼 구성해주는 함수
 */
function setPagination(page, response) {
	
	// pagination UI 구성을 위해 필요한 정보들
	const maxSize = 5;
	const startPage = Math.floor(page / maxSize) * maxSize + 1;
	const lastPage = startPage + (maxSize - 1) > response.totalPages ? response.totalPages : startPage + (maxSize - 1);
	
	let pagination = `
		<a class="arrow pprev" id="pprev" href="javascript:void(0)" onClick="getBoardsPage(${startPage - 2})"></a>
      	<a class="arrow prev" id="prev" href="javascript:void(0)" onClick="getBoardsPage(${page - 1})"></a>
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
		<a class="arrow next" id="next" href="javascript:void(0)" onClick="getBoardsPage(${page + 1})"></a>
		<a class="arrow nnext" id="nnext" href="javascript:void(0)" onClick="getBoardsPage(${startPage + maxSize - 1})"></a>
	`;
	
	$('#page_nation').empty().append(pagination);
	
	setPaginationArrowVisible(page + 1, startPage, lastPage, response.totalPages);
}

/**
 * 페이지네이션 앞,뒤 화살표 버튼 보임 상태 설정
 */
function setPaginationArrowVisible(currentPage, startPage, lastPage, totalPages) {
	// 이전 블럭이 존재하지 않는 경우 : << 버튼을 숨기기
	if (startPage == 1) {
		switchHidden("pprev");
	} else {
		switchVisible("pprev")
	}
	
	// 이전 페이지가 존재하지 않는 경우 : < 버튼을 숨기기
	if (currentPage == 1) {
		switchHidden("prev");
	} else {
		switchVisible("prev");
	}
	
	// 다음 블럭이 존재하는 경우 : >> 버튼을 숨기기
	if (lastPage >= totalPages) {
		switchHidden("nnext");
	} else {
		switchVisible("nnext");
	}
	
	// 다음 페이지가 존재하지 않는 경우 : > 버튼을 숨기기
	if (currentPage == totalPages) {
		switchHidden("next");
	} else {
		switchVisible("next");
	}
}

/**
 * Dom 요소의 Id를 받아서 visiblility를 hidden으로 설정
 */
const switchHidden = (elementId) => {
	document.getElementById(elementId).style.visibility = "hidden";
}

/**
 * Dom 요소의 Id를 받아서 visiblility를 visible로 설정
 */
const switchVisible = (elementId) => {
	document.getElementById(elementId).style.visibility = "visible";
}


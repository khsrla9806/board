
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
	
	let requestURL = `/api/v1/boards?page=${page}`;
	
	// 여기서 keyword의 Null 처리르 해줘야 한다. (Server까지 가면 그냥 'null'이라는 문자열로 처리된다.)
	const keyword = params.get('search');
	if (keyword) { // 여기서 null, undefined, 비어있는 문자 확인
		document.getElementById('search').value = keyword;
		requestURL += `&keyword=${keyword}`;
	}
	
	$.ajax({
		type: 'get',
		url: requestURL
	}).done(response => {
		$('#board-list').empty();
		
		if (response.data.length != 0) {
			response.data.forEach((board) => {
				let boardItem = getBoardItem(board, page);
				$('#board-list').append(boardItem);
			});	
		} else if (keyword != null) {
			$('#board-list').append(`
				<h2>검색하신 '${keyword}'에 대한 결과가 없습니다.</h2>
			`)
		} else {
			$('#board-list').append(`
				<h2>게시글이 존재하지 않습니다.</h2>
			`)
		}
		
		setPagination(page, response);
		
		// 현재 페이지 정보를 쿠키에 저장 (페이지 정보, 키워드 정보)
		document.cookie = `beforeHomePage=${page}`;
		if (keyword != null && !(typeof keyword == "undefined")) {
			document.cookie = `beforeHomeKeyword=${keyword}`;
		}
		
		// 게시글 리스트 상단으로 스크롤 이동
		window.scrollTo({top: 0, behavior: 'smooth'});
		
	}).fail(error => {
		console.log(error);
	});
}

/**
 * 최근 내가 작성한 게시글
 */
function getMyCurrentBoards() {
	const size = 5;
	
	$.ajax({
		type: 'get',
		url: `/api/v1/boards/my?size=${size}`
	}).done(response => {
		$('#my-current-board-list').empty();
		
		response.forEach((board) => {
			let currentBoardItem = getMyCurrentBoardItem(board);
			$('#my-current-board-list').append(currentBoardItem);
		});
		
	}).fail(error => {
		console.log(error);
	});
}

const getMyCurrentBoardItem = (board) => {
	
	// 목록 보기에서는 태그 모두 지움
	let content = board.content.replace(/(<([^>]+)>)/gi, "");
	
	let item = `
		<div class="my-current-board-item" onClick="location.href='/board/${board.id}'">
			<div class="my-current-board-title">${board.title}</div>
			<div class="my-current-board-content">${content}</div>
		</div>
	`;
	
	return item;
}

// 홈으로 재로딩하게 되면 검색 키워드 쿠키 내용은 초기화
deleteCookie("beforeHomeKeyword");

let params = new URLSearchParams(location.search);
let page = params.has('page') ? parseInt(params.get('page')) : 0;

// 로그인되어있는 상태인지 확인
const loginMember = document.getElementById("loginMember").value;

if (loginMember) {
	getMyCurrentBoards(); // 로그인된 사용자가 있을 때만 실행되는 영역
}
getBoardsPage(page);

/**
 * board 데이터 응답 형태를 받아서 HTML에 넣어줄 요소를 반환하는 함수
 */
function getBoardItem(board, page) {
	
	// 목록 보기에서는 태그 모두 지움
	let content = board.content.replace(/(<([^>]+)>)/gi, "");
	
	let item = `
			<div class="content-element" onClick="location.href='/board/${board.id}?page=${page}'">
				<div class="board-title" id="board-title-value">
					${board.title}
				</div>
				
				<div class="board-content" id="board-content-value">
					${content}
				</div>
				
				<div class="board-created-date">
					생성일시: <span id="board-created-date-value">${board.createdDate}</span>
				</div>
				
				<div class="board-bottom-area">
					<div class="board-writer-nickname">
						작성자: <span id="board-writer-nickanme-value">${board.writerNickname}</span>
					</div>
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
	if (currentPage == totalPages || totalPages == 0) {
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


/**
 * 로그아웃
 */
function logout() {
	$.ajax({
		type: "post",
		url: "/auth/logout"
	}).done(response => {
		alert("로그아웃 성공");
		location.href = "/home";
	}).fail(error => {
		console.log("error 발생 : " + error);
	});
}

/**
 * 쿠키를 등록하는 메서드
 */
function setCookie(name, value, options = {}) {

  options = {
    path: '/',
    // 필요한 경우, 옵션 기본값을 설정할 수도 있습니다.
    ...options
  };

  if (options.expires instanceof Date) {
    options.expires = options.expires.toUTCString();
  }

  let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

  for (let optionKey in options) {
    updatedCookie += "; " + optionKey;
    let optionValue = options[optionKey];
    if (optionValue !== true) {
      updatedCookie += "=" + optionValue;
    }
  }

  document.cookie = updatedCookie;
}

/**
 * 쿠키 삭제 메서드
 */
function deleteCookie(name) {
  setCookie(name, "", {
    'max-age': -1 // 만료 기간을 음수로 하면 쿠키는 삭제된다.
  })
}
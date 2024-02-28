# Board

## Used Tech Stack
- `java` (JDK 11)
- `Springboot` (2.7.15)
- `eclipse`
- `maven`
- `mybatis`
- `mysql`
- `javascript`, `html`, `css`, `JSP` (UI)
- `ajax` (asynchronous request/response)

<br>

## Dependency
- `mysql-connector-java` 8.0.33
- `mybatis-spring-boot-starter` 3.0.0
- `spring-data-commons`
- `jstl`
- `lombok`

<br>

## Implementation
### Board
- `게시글 생성`
- `게시글 단건 조회`
- `게시글 수정`
- `게시글 삭제`
- `게시글 페이지 조회`
  - 상세 페이지에서 이전 페이지 이동 시 검색 키워드 및 페이지 정보 유지 (쿠키 사용)
- `제목 키워드로 게시글 검색`

### Auth
- `회원가입`
  - nickname, password, email, address를 받아서 회원가입
  - address의 경우에는 행안부에서 제공하는 주소 검색 API를 적용해서 Full Address를 사용
- `로그인`
- `로그아웃`
- `게시글 수정, 삭제, 생성 권한 설정`
  - Spring의 ArgumentResolver를 이용하여 LoginMember 객체로 세션 정보 바인딩
 
<br>

## UI
### Main Page
![image](https://github.com/khsrla9806/board/assets/70641477/36965365-6a92-4baf-8b52-9eca9f7290de)
- 검색할 수 있는 영역
- 게시글 페이지네이션 목록 리스트
- 로그인/회원가입/로그아웃 영역
- 사이드바: 내가 최근에 작성한 게시글 영역 (5개까지 표시, 최신순)

### Sign-up, Sign-in Page
![image](https://github.com/khsrla9806/board/assets/70641477/c67d3be5-a57d-4e99-b70a-a0428dee63ec)
> 회원가입 페이지

![image](https://github.com/khsrla9806/board/assets/70641477/be0053d0-aaef-4542-955b-1d60f5a70dd6)
> 회원가입 주소 검색

![image](https://github.com/khsrla9806/board/assets/70641477/bfc09191-c012-41da-b304-05c68388d123)
> 로그인 페이지

### Board Detail Page
![image](https://github.com/khsrla9806/board/assets/70641477/7a7c9a8b-0e35-4aec-9331-041b5391ecda)
> 게시글 상세 페이지

![image](https://github.com/khsrla9806/board/assets/70641477/1686904c-4923-4fe9-99b8-50c6a5fbb303)
> 게시글 수정, 삭제 버튼 활성화 (내가 작성한 게시글인 경우)

- 로그인 상태, 권한 여부에 따라서 "수정하기", "삭제하기" 버튼의 유무  
- "뒤로가기" 눌렀을 때, 이전 페이지의 검색 키워드, 페이지 정보를 기억후 돌아감
- 작성일자, 수정일시의 경우 년,월,일,시간,분 포멧으로 출

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항보기</title>
</head>
<body>
<table>
<tr><td><a href="Nboard.do">공지사항</a></td><td><a>자유게시판</a></td><td><a href="QAboard.do">Q&A게시판</a></td></tr>
</table>
<div>
${login.name}님 환영합니다.[<a href="">마이페이지</a>|<a href="../Member/logout.do">로그아웃</a>] 
<!-- ../= 현재위치의 상단 폴더로 이동 /logout을 하기위해선  board에서 나와 Member로 가야하는데 board에서 나와 views에서
Member로 들어가야 됨-->
</div>
<hr>

| 공지사항
<hr>
<form>
	<table>
		<tr>
			<td>
				작성자
			</td>
			<td>
			</td>
			<td>
				등록일
			</td>
			<td>
			
			</td>
			<td>
				조회수
			</td>
			<td>
			
			</td>
			
		</tr>
		<tr>
			<td>
				제목
			</td>
			<td>
			
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>
				첨부파일
			</td>
			<td>
			</td>
		</tr>
	</table>
<button type="button">삭제</button>
<button type="button">수정</button>
<button type="button">목록</button>
</form>
</body>
</html>
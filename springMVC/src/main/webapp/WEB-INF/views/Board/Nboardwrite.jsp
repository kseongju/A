<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
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
<form action="Nboardwrite.do" method="post" >

<table>
	<tr>
		<td>
			제목
		</td>
		<td>
			<input type="text" name="title">
		</td>
	</tr>
	<tr>
		<td colspan="2"><textarea rows="20" cols="100" name="content"></textarea></td>
	</tr>
	<tr>
		<td>
			첨부파일
		</td>
		<td>
			<input type="file" name="file">
		</td>
	</tr>

</table>
<button type="button" onclick="location.href='Nboard.do'">취소</button>
<button>확인</button>
</form>
</body>
</html>
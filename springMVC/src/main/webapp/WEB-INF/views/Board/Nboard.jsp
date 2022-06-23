<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<table>
<tr><td>공지사항</td><td>자유게시판</td><td>Q&A게시판</td></tr>
</table>
<div>
님 환영합니다.[<a href="/Member/logout.do">마이페이지</a>|<a href="">로그아웃</a>]
</div>
<hr>

| 공지사항
<hr>
<form>
검색
	<select>
		<option>전체</option>
		<option>제목+내용</option>
		<option>작성자</option>
	</select>
	<input type="text" name="SearchValue">
	<button type="button">검색</button>
</form>

-총 *건이 검색되었습니다.
<form>
<table>
	<thead>
		<tr>
			<th>
				No
			</th>
			<th>
				제목
			</th>
			<th>
				작성자
			</th>
			<th>
				등록일
			</th>
			<th>
				조회수
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>
</form>
<form>페이징 처리</form>
<button type="button">등록</button>
</body>
</html>
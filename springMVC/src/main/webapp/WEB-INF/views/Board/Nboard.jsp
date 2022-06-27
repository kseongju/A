<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
			<c:if test="${list.size() == 0 }">
				<tr>
					<td colspan="4">등록 된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${list.size() > 0}">
				<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.bidx}</td>
						<td><a href="Nboardcontent.do?bidx=${vo.bidx}">${vo.title}</a></td>
						<td>${vo.name}</td>
						<td>${vo.wdate}</td>
					</tr>
				</c:forEach>
			</c:if>
	</tbody>
</table>
</form>
<form>페이징 처리</form>
<c:if test="${login.id eq 'admin'}">
<button type="button" onclick="location.href='Nboardwrite.do'">등록</button>
</c:if>
</body>
</html>
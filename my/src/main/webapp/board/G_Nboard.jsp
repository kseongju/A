<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="domain.BoardVo" %>
   <%@ page import = "java.util.*" %>
   
   <%
	ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/QuizNboard.css" >
</head>
<body>

	<% 
int midx = 0;
if(session.getAttribute("midx") != null) {
	midx = (int)session.getAttribute("midx");
	}%>

<header>
<h1><a href="<%=request.getContextPath()%>/board/main.do">G</a></h1>
</header>
<nav>
<%if(midx==1){ %> <!-- 관리자 계정 midx가 1임 -->

	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/board/QuizNboard.do">공지사항</a></li>
			<li><a href="<%=request.getContextPath()%>/board/QuizFboard.do">자유게시판</a></li>
			<li><a href="#">퀴즈목록</a></li>
		</ul>
	</div>
<% }else{%>
	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/board/QuizNboard.do">공지사항</a></li>
			<li><a href="<%=request.getContextPath()%>/board/QuizFboard.do">자유게시판</a></li>
			
		</ul>
	</div>
<% }%>
</nav>
<main>
<form name="frm" action="<%=request.getContextPath() %>/board/QuizFboard.do" method="post">
<table border=0; style=" width :200px ;text-align:left">
<tr>
<td style="width:680px">
<select name="searchType">
<option value="title">제목</option>
<option value="writer">작성자</option>
</select>
</td>
<td>
<input type="text" name="keyword" size="10">
</td>
<td>
<input type="submit" name="submit" value="검색">
</td>

</tr>
</table>
</form>
<br>
<%if(midx==1){ %>
<form>
<button type="button" id="nboard_btn" onclick="location.href='<%=request.getContextPath() %>/board/Nboardwrite.do'">작성하기</button>
<% } %>
</form>
<form id="nboard">
	<table>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<% for (BoardVo bv : alist) { %>
<tr>
<td><%=bv.getBidx() %></td>


<td>

<a href="<%=request.getContextPath() %>/board/Nboardcontent.do?nbidx=<%=bv.getBidx() %>"><%=bv.getTitle() %></a>
<%--제목에 링크를 달아 게시판보기 페이지로 이동 --%>


</td>


<td><%=bv.getWriter() %></td>
<td><%=bv.getWriteday() %></td>
<td><%=bv.getHit() %></td>
</tr>
<% } %>
		
	</table>
</form>
</main>
<footer>
</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="domain.*" %>
<%

	BoardVo bv = (BoardVo)request.getAttribute("bv"); //강제 형변환


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 공지사항 수정하기</title>
<script>
	function check(){
		//alert("tset");
		
		var fm=document.board;

		fm.action= "<%=request.getContextPath()%>/board/NboardmodifyAction.do";
		fm.method = "post";
		fm.submit();

	 return;
		
		
	}
	
</script>
</head>
<body>
	<% 

	
int midx = 0;
if(session.getAttribute("midx") != null) {
	midx = (int)session.getAttribute("midx");
	}
	
	%>

<h1>게시글 수정하기</h1>

<form name="board">
<input type="hidden" name ="nbidx" value="<%= bv.getBidx()%>"> <!-- 컨트롤러에서 bidx값을 받을 수 있게 hidden으로 bidx값을 가져옴 -->
<table border ="1">

<tr>
<td>
제목
</td>
<td>
<input type="text" size="78" name = "title" style="border:0 solid black" value="<%=bv.getTitle() %>" >
</td>
</tr>

<tr>
<td>
내용
</td>
<td>
<textarea  rows = "20" cols ="80" name="content"><%=bv.getContent() %></textarea>
</td>
</tr>

<tr>
<td>
작성자
</td>
<td>
<input type ="text" name="writer" style="border: none"  value="<%=session.getAttribute("username")%>" readonly="readonly">
</td>
</tr>

<tr>
<td colspan="2" align="center">

<input type="button" value="수정완료" onclick="check()">


</td>

</tr>
</table>
</form>


</body>
</html>
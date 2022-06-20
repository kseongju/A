<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지쓰기</title>
<script>
	function check(){
		//alert("tset");
		
		var fm=document.board;

		fm.action= "<%=request.getContextPath()%>/board/NboardwriteAction.do";
		fm.method = "post";
		fm.submit();

	 return;

	}
	
</script>
</head>
<body>
<h1>게시판 글쓰기</h1>

<form name="board">

<table border ="1">

<tr>
<td>
제목
</td>
<td>
<input type="text" size="78" name = "title" style="border:0 solid black" placeholder="제목을 입력하세요">
</td>
</tr>

<tr>
<td>
내용
</td>
<td>
<textarea  rows = "20" cols ="80" name="content" placeholder="내용을 입력하세요"></textarea>
</td>
</tr>

<tr>
<td>
작성자
</td>
<td>
<input type ="text" name="writer" style="border: none" value="<%=session.getAttribute("username")%>" readonly="readonly">
</td>
</tr>

<tr>
<td colspan="2" align="center">

<input type="button" value="확인" onclick="check()">
<input type="reset" value="다시쓰기">

</td>

</tr>
</table>
</form>


</body>
</html>
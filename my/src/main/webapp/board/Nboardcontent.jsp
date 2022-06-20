<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.BoardVo" %>
<%@ page import = "java.util.*" %>
<%

	BoardVo bv = (BoardVo)request.getAttribute("bv"); //강제 형변환
	ArrayList<BoardVo> nclist = (ArrayList<BoardVo>)request.getAttribute("nclist");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 내용보기</title>
<script>
function del(){
	
	 var fm=document.board; 
	
	if(confirm("정말 삭제하시겠습니까?"))
		
	fm.action= "<%=request.getContextPath()%>/board/nboardDeleteAction.do"; //삭제하기 클릭시 실행
	fm.method = "post";
	fm.submit();

 return;
	
}

function reply(){
	
	var fm=document.re;
	
	fm.action = "<%=request.getContextPath()%>/board/nboardreplyAction.do";
	fm.method="post";
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
	} //세션 안에 있는 midx를 int midx에 담는다.
	
	%>


<form name="board">
<input type="hidden" name ="nbidx" value="<%= bv.getBidx()%>"> <!-- 컨트롤러에서 bidx값을 받을 수 있게 hidden으로 bidx값을 가져옴 -->

<table border ="1">

<tr>
<td>
제목
</td>
<td>
<input type="text" size="68" style="border: none" id="not" value="<%=bv.getTitle() %>"> <%--bv에 담긴 값을 담음--%>
</td>
<td>
조회수
</td>
<td width="50px"><%=bv.getHit() %></td>
</tr>

<tr>
<td >
내용
</td>
<td colspan="3">
<textarea  rows = "20" cols ="90" id="not"><%=bv.getContent() %></textarea>
</td>
</tr>



<tr>
<td >
작성자
</td>
<td colspan="3">
<input type ="text"id="not" style="border: none" value="<%=bv.getWriter() %>">
</td>
</tr>
</table>
</form>
<%if(midx==1){ %> <!-- 관리자만 보이게 설정 -->
<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/Nboardmodify.do?nbidx=<%=bv.getBidx() %>'">수정하기</button>
<button type="button" onclick="del()">삭제하기</button>
<% } %>
<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/G_Nboard.do'">목록으로</button>


<form>
<h2>댓글<%=bv.getCcount() %></h2>

<%for(BoardVo abv : nclist){ %>
<div>
<%=abv.getWriter() %><br>
<%=abv.getContent() %><br>
<%=abv.getWriteday() %>
</div><br>
<% }%>
</form>



<form name=re>
<input type="hidden" name ="nbidx" value="<%= bv.getBidx()%>">

<textarea name="content"></textarea>

<button type="button" onclick="reply()">댓글달기</button>
</form>

</body>
</html>
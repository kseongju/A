<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import="domain.*" %>
<% 
	ArrayList<MemberVo> mlist = (ArrayList<MemberVo>)request.getAttribute("mlist");
%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<table>
		<thead>
			<tr>
				<th>회원번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>닉네임</th>
				<th>전화번호</th>
				<th>가입날짜</th>
				<th>상태관리</th>
				
			</tr>
		</thead>
		<tbody>
		<% for(MemberVo mv : mlist){%>
			<tr>
				<td><%=mv.getMidx() %></td>
				<td><%=mv.getMemberName() %></td>
				<td><%=mv.getMemberID() %></td>
				<td><%=mv.getUserName() %></td>
				<td><%=mv.getMemberPhone() %></td>
				<td><%=mv.getCreationdate() %></td>
				<% if(mv.getDelyn().equals("N")){ %>
				<td><button type="button" onclick="">정지</button></td>
				<% }else{ %>
				<td><button type="button" onclick="">복구</button></td>
				<% } %>
			</tr>
		<% } %>
		</tbody>
	</table>
</form>









</body>
</html>
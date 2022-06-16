<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "srvice.MemberDao" %>
    <%@ page import = "domain.MemberVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String memberName = request.getParameter("membername");
String memberPhone = request.getParameter("memberphone");




MemberDao md = new MemberDao();
String findid = md.memberfindid(memberName, memberPhone); %>

<form name="find">

<% if(findid==null) { %>
 <h4>등록되지 않은 정보입니다.</h4>
	
<% }else{ %>

<div>
 <h4>회원님의 아이디는</h4>
	<div><%= findid %></div>
 <h4>입니다.</h4>

</div>


<% } %>
</form>

</body>
</html>
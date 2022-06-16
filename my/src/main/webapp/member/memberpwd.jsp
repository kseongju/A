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
String memberID = request.getParameter("memberid");
String memberPhone = request.getParameter("memberphone");




MemberDao md = new MemberDao();
String findpwd = md.memberfindpwd(memberName, memberID, memberPhone); %>

<form name="find">

<% if(findpwd==null) { %>
 <h4>등록되지 않은 정보입니다.</h4>
	
<% }else{ %>

<div>
 <h4>회원님의 비밀번호는</h4>
	<div><%= findpwd %></div>
 <h4>입니다.</h4>

</div>


<% } %>
</form>

</body>
</html>
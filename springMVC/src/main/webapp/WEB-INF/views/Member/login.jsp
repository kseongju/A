<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="${path}/resources/css/login.css" rel="stylesheet"/> 	
</head>
<body>

<h5>해당 시스템은 로그인 후 이용가능합니다.</h5>
<hr>
<form name="frm" id="login">
<table>
<tr>
<td style="text-align:right">아이디 : </td>
<td> <input type="text" name="ID"></td>
</tr>
<tr>
<td>비밀번호 : </td>
<td> <input type="password" name="password"></td>
</tr>
</table>
<button id="btn">로그인</button><br>
<a href="join.do">회원가입</a>
</form>

</body>
</html>
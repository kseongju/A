<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF_8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css" >

<script>
	function check(){
		//alert("test");
		
		var fm=document.Login;
		
		fm.action= "<%=request.getContextPath()%>/member/memberLoginAction.do";
		fm.method = "post";
		fm.submit();
		
		return;
	}

</script>
</head>
<body>
<form name="Login" id="Login">

			<h1>로그인</h1>
			<div id="id">
			<h3>아이디</h3>
			<input type="text" name="memberid">
			</div>
			
			<div id="pwd">
			<h3>비밀번호</h3>
			<input type="password" name="memberpwd">
			</div>
			<button type="button" value="Log-in" id="Login_btn" onclick="check()">Log-in</button>
		
			<div id="idfind"><a href="<%=request.getContextPath()%>/member/memberfindid.do">아이디</a></div>
			<div id="idfind">|</div>
			<div id="pwdfind"><a href="<%=request.getContextPath()%>/member/memberfindpwd.do">비밀번호 찾기</a></div>
			<div id="memberjoin"><a href="<%=request.getContextPath()%>/member/memberjoin.do">회원가입</a></div>

</form>

</body>
</html>
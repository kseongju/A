<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="domain.MemberVo" %>
    <%@page import="srvice.MemberDao" %>
    
    <%

	MemberVo mv = (MemberVo)request.getAttribute("mv"); //강제 형변환


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정관리</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberjoin.css" >
<script>
	function check(){
		//alert("test");
		
		var fm=document.Accountfm;
		
		fm.action= "<%=request.getContextPath()%>/member/memberAccountAction.do";
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
	}%>


<form name="Accountfm">

<div id="header">
	<div id="wrapper">
		<div id="content">
		<input type="hidden" value="<%=midx %>">
			<div>
			<h3>
				<label for="username">닉네임</label>
			</h3>
			<span>
				<input type="text" id = "username" name="username" maxlength="12" value="<%=mv.getUserName()%>">
			</span>
			
			</div>
			<div>
			<h3>
				<label for="id">아이디</label>
			</h3>
			<span class="box int_id">
				<input type="text" id="id" name="memberid" value="<%=mv.getMemberID() %>">
			</span>	
			</div>
			<div>
			<h3>
				<label for="pwd">비밀번호</label>
			</h3>
				<input type="password" id="pwd" name="memberpwd" maxlength="16" value="<%=mv.getMemberPwd() %>">
			</div>
			<div>
			<h3>
				<label for="pwdcheck">비밀번호 확인</label>
			</h3>
				<input type="password" id="pwdcheck" name="memberpwdcheck" maxlength="16" value="<%=mv.getMemberPwd() %>" >
			</div>
		
			
			<div class="btn_area">
				<button id="btnJoin" type="button" onclick="check();">
					<span>저장하기</span>
				</button>
			</div>
		</div>
	</div>
</div>	
</form>
</body>
</html>
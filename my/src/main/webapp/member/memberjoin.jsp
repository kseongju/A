<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberjoin.css" >
<script>
function check() {
	//alert("테스트");
	var fm=document.Join;
	if(fm.membername.value==""){
		alert("이름을 입력하세요");
		fm.membername.focus(); 
		return;
	}
		else if(fm.username.value==""){
		alert("닉네임을 입력하세요");
		fm.username.focus();
		return;
	}
		else if(fm.memberid.value==""){
			alert("아이디를 입력하세요");
			fm.memberid.focus();
			return;
	}	else if(fm.memberpwd.value==""){
			alert("비밀번호를 입력하세요");
			fm.memberpwd.focus();
			return;
	}	else if(fm.memberpwdcheck.value!=fm.memberpwd.value){
			alert("비밀번호와 일치하지 않습니다.")
			fm.memberpwdcheck.focus();
			return;
	}	else if(fm.memberphone.value==""){
			alert("전화번호를 입력하세요")
			fm.memberphone.focus();
			return;
	}
	
	fm.action= "<%=request.getContextPath()%>/member/memberJoinAction.do";
	fm.method = "post";
	fm.submit();
	
	return;
	
}

</script>
</head>
<body>
<form name="Join">
<div id="header">
<h1>
	Q
</h1>
	<div id="wrapper">
		<div id="content">
			<div>
			<h3>
				<label for="membername">이름</label>
			</h3>
			<span>
				<input type="text" id = "membername" name="membername" maxlength="10">
			</span>
			</div>
			<div>
			<h3>
				<label for="username">닉네임</label>
			</h3>
			<span>
				<input type="text" id = "username" name="username" maxlength="12">
			</span>
			
			</div>
			<div>
			<h3>
				<label for="id">아이디</label>
			</h3>
			<span class="box int_id">
				<input type="text" id="id" name="memberid">
			</span>	
			</div>
			<div>
			<h3>
				<label for="pwd">비밀번호</label>
			</h3>
				<input type="password" id="pwd" name="memberpwd" maxlength="16">
			</div>
			<div>
			<h3>
				<label for="pwdcheck">비밀번호 확인</label>
			</h3>
				<input type="password" id="pwdcheck" name="memberpwdcheck" maxlength="16">
			</div>
			<div>
			<h3>
				<label for="phone">전화번호</label>
			</h3>
				<input type="text" id="phone" name="memberphone" maxlength="13" placeholder="010-****-****">
			</div>
			<div>
			<h3>
				<label for="memberemail">이메일(선택사항)</label>
			</h3>
				<input type="email" id="memberemail" placeholder="test@naver.com">
			</div>
			<div class="btn_area">
				<button id="btnJoin" type="button" onclick="check();">
					<span>회원가입</span>
				</button>
   			</div>
		</div>	
	</div>
</div>
</form>



</body>
</html>
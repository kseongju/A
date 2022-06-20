<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberjoin.css" >
<script>
function check(){
	
	var fm=document.find;
	
	fm.action= "<%=request.getContextPath()%>/member/memberid.do";
	fm.method = "post";
	fm.submit();
	
	return;
	
	
}

</script>
</head>
<body>
<form name="find">
<div id="header">
	<div id="wrapper">
		<div id="content">
			
			<div>
			<h3>
				<label for="name">이름</label>
			</h3>
				<input type="text" id="name" name="membername" maxlength="16" value="">
			</div>
			<div>
			<h3>
				<label for="phone">전화번호</label>
			</h3>
				<input type="text" id="phone" name="memberphone" maxlength="13" value="">
			</div>
			
		
			
			<div class="btn_area">
				<button id="btnfind" type="button" onclick="check();">
					<span>입력완료</span>
				</button>
			</div>
			
		</div>
	</div>
</div>	
</form>
</body>
</html>
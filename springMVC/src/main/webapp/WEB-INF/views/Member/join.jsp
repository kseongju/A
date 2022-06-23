<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script>
function IDcheck(){
	 if ($('#id').val() != '') {
			
	        // 아이디를 서버로 전송 > DB 유효성 검사 > 결과 반환받기
	        $.ajax({
	   					
	            type: 'post',
	            url: 'idcheck.do',
	            data: 'id=' + $('#id').val(),
	            success: function(result) {
	                if (result == '0') {
	                    $('#result').text('사용 가능한 아이디입니다.');
	                } else {
	                    $('#result').text('이미 사용중인 아이디입니다.');
	                }
	            },
	            error: function(a, b, c) {
	                console.log(a, b, c);
	            }
	   					
	        });
	   				
	    } else {
	        alert('아이디를 입력하세요.');
	        $('#txtid').focus();
	    }
}




</script>
</head>
<body>
<h5>회원 가입 후 로그인하여 이용하세요</h5>
<hr>
<form action="join.do" method="post">
	<table>
		<tr>
			<td>
				아이디 :
			</td>
			<td>
				<input type="text" name="id" id="id">
				<span id="result"></span>
			</td>
			<td>
				<button type="button" id="idcheck" onclick="IDcheck()">아이디 중복확인</button>
			</td>
		</tr>
		<tr>
			<td>
				비밀번호 :
			</td>
			<td>
				<input type="password" name="password">
			</td>
		</tr>
		<tr>
			<td>
				이름 :
			</td>
			<td>
				<input type="text" name="name">
			</td>
		</tr>
		<tr>
			<td>
				이메일 :
			</td>
			<td>
				<input type="email" name="email">
			</td>
		</tr>
		<tr>
			<td>
				연락처 : 
			</td>
			<td>
				<input type="text" name="phone">
			</td>
		</tr>
		<tr>
			<td>
				주소 :
			</td>
			<td>
				<input type="text" name="addr">
			</td>
		</tr>
	</table>
	<button>회원가입</button>
</form>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="<%= request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script>

var result2 = 3;

function IDcheck(){
	 if ($('#id').val() != '') {
			
	        // 아이디를 서버로 전송 > DB 유효성 검사 > 결과 반환받기
	        $.ajax({
	   					
	            type: 'post',
	            url: 'idcheck.do',
	            data: 'id=' + $('#id').val(),
	            success: function(result) {
	                if (result == '0') {
	                	result2 = 0
	                    $('#result').text('사용 가능한 아이디입니다.');
	                } else {
	                	result2 = 1
	                    $('#result').text('이미 사용중인 아이디입니다.');
	                }
	            },
	            error: function(a, b, c) {
	                console.log(a, b, c);
	            }
	   					
	        });
	   				
	    } else {
	        alert('아이디를 입력하세요.');
	        $('#id').focus();
	    }
}


function joinok(){
	
	if(result2 ==0){
		$("#joinfrm").submit();
	}else{
		$('#id').focus();
	}
	
	
}

</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function execPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
           
           $("[name=addr1]").val(data.zonecode);
           $("[name=addr2]").val(fullRoadAddr);
           
           /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
           document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
       }
    }).open();
}


</script>
</head>
<body>
<h5>회원 가입 후 로그인하여 이용하세요</h5>
<hr>
<form action="join.do" method="post" id="joinfrm">
	<table>
		<tr>
			<td>
				아이디 :
			</td>
			<td>
				<input type="text" name="id" id="id">
				<button type="button" id="idcheck" onclick="IDcheck()">아이디 중복확인</button>
			</td>
			<td>
				<span id="result"></span>
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
				<div class="form-group">                   
<input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="addr1" id="addr1" type="text" readonly="readonly" >
    <button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>                               
</div>
<div class="form-group">
    <input class="form-control" style="top: 5px;" placeholder="도로명 주소" name="addr2" id="addr2" type="text" readonly="readonly" />
</div>
<div class="form-group">
    <input class="form-control" placeholder="상세주소" name="addr3" id="addr3" type="text"  />
</div>
				
			</td>
		</tr>
	</table>
	<button type="button" onclick="joinok()">회원가입</button>
</form>



</body>
</html>
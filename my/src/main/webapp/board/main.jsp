<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.*" %>
<%@ page import="srvice.*" %>
<%@ page import = "java.util.*" %>
        
<%
  ArrayList<BoardVo> mnlist = (ArrayList<BoardVo>)request.getAttribute("mnlist");
  ArrayList<BoardVo> mflist = (ArrayList<BoardVo>)request.getAttribute("mflist");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script>
    $(function(){ //로딩화면
       $(".loading").fadeOut(); //3초에 걸쳐 천천히 사라짐
       
    });
    
    function NO(){
    	
    	alert("개발예정입니다.");
    	
    }
    
    var index = 0;   //이미지에 접근하는 인덱스
    window.onload = function(){
        slideShow();
    }
    
    function slideShow() {
    var i;
    var x = document.getElementsByClassName("slide1");  //slide1에 대한 dom 참조
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
    }
    index++;
    if (index > x.length) {
        index = 1;  //인덱스가 초과되면 1로 변경
    }   
    x[index-1].style.display = "block";  //해당 인덱스는 block으로
    setTimeout(slideShow, 4000);   //함수를 4초마다 호출
 
}

</script>

</head>
<body>
<div class="loading">
<h2>로딩중입니다.</h2>
<input type="image" src="../image/loading.gif">


</div>
<%




%>
<%
		// 메인 페이지로 이동했을 때 세션에 값이 담겨있는지 체크
		String memberid = null;
		if(session.getAttribute("memberid") != null){
			memberid = (String)session.getAttribute("memberid");
		}
	%>
	
	<% 
int midx = 0;
if(session.getAttribute("midx") != null) {
	midx = (int)session.getAttribute("midx");
	}%>

<header>
<h1>G</h1>
</header>
<nav>

<%if(midx==1){ %> <!-- 관리자 계정 midx가 1임 -->

	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/board/QuizNboard.do">공지사항</a></li>
			<li><a href="<%=request.getContextPath()%>/board/QuizFboard.do">자유게시판</a></li>
		</ul>
	</div>
<% }else{%>
	<div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/board/QuizNboard.do">공지사항</a></li>
			<li><a href="<%=request.getContextPath()%>/board/QuizFboard.do">자유게시판</a></li>
			
		</ul>
	</div>
<% }%>
</nav>

<main>
<% if(memberid==null){ %>
<br>
<form name="Login_fm"id="Login">
<h3>로그인</h3><br>

<button type="button" id="Login_btn" onclick = "location.href='/my/member/memberLogin.do'">Log-in</button><br>
<div id="idfind"><a href="<%=request.getContextPath()%>/member/memberfindid.do">아이디</a></div>
<div id="idfind">|</div>
<div id="pwdfind"><a href="<%=request.getContextPath()%>/member/memberfindpwd.do">비밀번호 찾기</a></div>
<div id="memberjoin"><a href="<%=request.getContextPath()%>/member/memberjoin.do">회원가입</a></div>

</form>
<% }else{ %>
<br>
<form name="Login_fm" id="Login">
<span id="username">
<%

if(memberid != null){
	out.println(session.getAttribute("username")+"님"); //닉네임 변경시 로그아웃이 필요. 가져온 세션값을 계속 유지해서 수정해도 바로 적용이 안되는듯.
}

%></span>

<div id="Logout"><a href="<%=request.getContextPath()%>/member/memberLogout.do">로그아웃</a></div>
<div id="memberAccount"><a href="<%=request.getContextPath()%>/member/memberAccount.do?midx=<%=midx%>">계정관리</a></div>

</form>
<% }%>

<form id="nboard">
<table>
<tr>
<td>글번호</td>
<td>제목</td>
<td>작성자</td>
<td>작성일</td>
<td>조회수</td>
</tr>
<%for(BoardVo bv : mnlist){ %>

<tr>
<td><%=bv.getBidx() %></td>
<td><a href="<%=request.getContextPath() %>/board/Nboardcontent.do?nbidx=<%=bv.getBidx() %>"><%=bv.getTitle() %>
<% if(bv.getCcount() !=0){ %> <!-- 댓글이 0이 아닐때 표시 -->
(<%=bv.getCcount() %>)
<% } %></a></td>
<td><%=bv.getWriter() %></td>
<td><%=bv.getWriteday() %></td>
<td><%=bv.getHit() %></td>
</tr>

<% } %>
</table>
</form>

<div id="slideimg">
  <img class="slide1" src="../image/game1.png">
  <img class="slide1" src="" alt="개발예정">
  <img class="slide1" src="" alt="개발예정";>
</div>

<form id="fboard">
<table>
<tr>
<td>글번호</td>
<td>제목</td>
<td>작성자</td>
<td>작성일</td>
<td>조회수</td>
</tr>
<%for(BoardVo bv : mflist){ %>

<tr>
<td><%=bv.getBidx() %></td>
<td><a href="<%=request.getContextPath() %>/board/Fboardcontent.do?fbidx=<%=bv.getBidx() %>"><%=bv.getTitle() %>
<% if(bv.getCcount() !=0){ %> <!-- 댓글이 0이 아닐때 표시 -->
(<%=bv.getCcount() %>)
<% } %></a></td>
<td><%=bv.getWriter() %></td>
<td><%=bv.getWriteday() %></td>
<td><%=bv.getHit() %></td>
</tr>

<% } %>
</table>
</form>



<br>



<button type="button" onclick="location.href='/my/board/Game.do'">게임1</button><br>
<button type="button" onclick="NO()">게임2</button>
</main>
<footer>

</footer>
</body>
</html>
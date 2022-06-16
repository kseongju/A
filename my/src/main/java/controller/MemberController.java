package controller;

import java.io.IOException;


import java.net.InetAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVo;

import srvice.MemberDao;


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//주소의 풀경로를 추출
				String uri = request.getRequestURI();
				//프로젝트 이름을 추출
				String pj = request.getContextPath();
				//프로젝트 이름을 뺀 나머지 가상경로를 추출
				String command = uri.substring(pj.length());
				//System.out.println("command:" + command);
				
	if(command.equals("/member/memberJoinAction.do")) { //회원가입 시도
		
		String memberName = request.getParameter("membername");
		String userName = request.getParameter("username");
		String memberID = request.getParameter("memberid");
		String memberPwd = request.getParameter("memberpwd");
		String memberPhone = request.getParameter("memberphone");
		String memberip = InetAddress.getLocalHost().getHostAddress();
		String memberemail = request.getParameter("memberemail");
		
		MemberDao md = new MemberDao();
		
		int value = md.insertMember(memberName, userName, memberID, memberPwd, memberPhone, memberip, memberemail);
		
		if(value==1) {
			response.sendRedirect(request.getContextPath()+"/board/main.do"); //성공시 메인페이지로
		}else {
			response.sendRedirect(request.getContextPath()+"/member/memberjoin.do"); //실패시 회원가입 페이지로
		}
		 
	}else if(command.equals("/member/memberjoin.do")) { //회원가입 클릭
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberjoin.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberfindid.do")) { //아이디 찾기 클릭
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberfindid.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberid.do")) { //아이디 찾기에서 버튼 클릭시
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberid.jsp");
		rd.forward(request, response);
		
		
	}else if(command.equals("/member/memberfindpwd.do")) { //비밀번호 찾기 클릭
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberfindpwd.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberpwd.do")) { //비밀번호 찾기에서 버튼 클릭시
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberpwd.jsp");
		rd.forward(request, response);
		
	
		
	}else if(command.equals("/member/memberAccount.do")) {
		
		
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		MemberDao md = new MemberDao();
		MemberVo mv = md.AccountSelect(midx);

	request.setAttribute("mv", mv);
	
	
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberAccount.jsp");
		rd.forward(request, response);	
		
	}else if(command.equals("/member/memberAccountAction.do")) {
		//System.out.println("들어옴");

		HttpSession session = request.getSession();
		

		String userName = request.getParameter("username");
		String memberID = request.getParameter("memberid");
		String memberPwd = request.getParameter("memberpwd");
		int midx = (int)session.getAttribute("midx");
		
		MemberDao md = new MemberDao();
		int value = md.memberAccount(userName, memberID, memberPwd, midx); //userName인데 memberuserName이라고 해놈.

		
		
		if(value==1) { //수정하기 성공
			
			response.sendRedirect(request.getContextPath()+"/board/main.do"); //했다면 메인페이지 이동
		}else {
			response.sendRedirect(request.getContextPath()+"/member/memberAccount.do"); //아니면 글수정하기 페이지로
		}

	}
	
	
	else if(command.equals("/member/memberLogin.do")) { //로그인클릭
		//System.out.println("로그인");
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberLoginAction.do")) {//로그인 시도
		//System.out.println("로그인중");
		//넘어온 값 받기(아이디,비밀번호)
		String memberID = request.getParameter("memberid");
		String memberPwd = request.getParameter("memberpwd");
		//System.out.println(request.getParameter("memberid"));
		
		//2.처리
		MemberDao md = new MemberDao();
		MemberVo mv = md.memberLogin(memberID, memberPwd);
		//System.out.println("처리중");

		HttpSession session = request.getSession();
		
		if (mv != null) { //아이디와 비번이 맞다면 
			session.setAttribute("midx", mv.getMidx());	//세션에 담긴 값을 가져옴
			session.setAttribute("memberid", mv.getMemberID());
			session.setAttribute("username", mv.getUserName());
		//	System.out.println(mv.getMidx());
			
			if (session.getAttribute("saveUrl") != null) { //그전에 있던 곳의 url이 있다면 
				response.sendRedirect((String)session.getAttribute("saveUrl"));	//해당주소로 이동 (String)session.getAttribute("saveUrl")
			}else {					
				response.sendRedirect(request.getContextPath()+"/board/main.do"); //없다면 메인페이지로 이동
			}
		
		}else {				//아니라면
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");	//로그인페이지로			
		}
	}else if (command.equals("/member/memberLogout.do")) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(request.getContextPath()+"/");
		
	}
	
	
	
	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

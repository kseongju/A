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
		
		//�ּ��� Ǯ��θ� ����
				String uri = request.getRequestURI();
				//������Ʈ �̸��� ����
				String pj = request.getContextPath();
				//������Ʈ �̸��� �� ������ �����θ� ����
				String command = uri.substring(pj.length());
				//System.out.println("command:" + command);
				
	if(command.equals("/member/memberJoinAction.do")) { //ȸ������ �õ�
		
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
			response.sendRedirect(request.getContextPath()+"/board/main.do"); //������ ������������
		}else {
			response.sendRedirect(request.getContextPath()+"/member/memberjoin.do"); //���н� ȸ������ ��������
		}
		 
	}else if(command.equals("/member/memberjoin.do")) { //ȸ������ Ŭ��
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberjoin.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberfindid.do")) { //���̵� ã�� Ŭ��
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberfindid.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberid.do")) { //���̵� ã�⿡�� ��ư Ŭ����
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberid.jsp");
		rd.forward(request, response);
		
		
	}else if(command.equals("/member/memberfindpwd.do")) { //��й�ȣ ã�� Ŭ��
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberfindpwd.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberpwd.do")) { //��й�ȣ ã�⿡�� ��ư Ŭ����
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
		//System.out.println("����");

		HttpSession session = request.getSession();
		

		String userName = request.getParameter("username");
		String memberID = request.getParameter("memberid");
		String memberPwd = request.getParameter("memberpwd");
		int midx = (int)session.getAttribute("midx");
		
		MemberDao md = new MemberDao();
		int value = md.memberAccount(userName, memberID, memberPwd, midx); //userName�ε� memberuserName�̶�� �س�.

		
		
		if(value==1) { //�����ϱ� ����
			
			response.sendRedirect(request.getContextPath()+"/board/main.do"); //�ߴٸ� ���������� �̵�
		}else {
			response.sendRedirect(request.getContextPath()+"/member/memberAccount.do"); //�ƴϸ� �ۼ����ϱ� ��������
		}

	}
	
	
	else if(command.equals("/member/memberLogin.do")) { //�α���Ŭ��
		//System.out.println("�α���");
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
		rd.forward(request, response);
		
	}else if(command.equals("/member/memberLoginAction.do")) {//�α��� �õ�
		//System.out.println("�α�����");
		//�Ѿ�� �� �ޱ�(���̵�,��й�ȣ)
		String memberID = request.getParameter("memberid");
		String memberPwd = request.getParameter("memberpwd");
		//System.out.println(request.getParameter("memberid"));
		
		//2.ó��
		MemberDao md = new MemberDao();
		MemberVo mv = md.memberLogin(memberID, memberPwd);
		//System.out.println("ó����");

		HttpSession session = request.getSession();
		
		if (mv != null) { //���̵�� ����� �´ٸ� 
			session.setAttribute("midx", mv.getMidx());	//���ǿ� ��� ���� ������
			session.setAttribute("memberid", mv.getMemberID());
			session.setAttribute("username", mv.getUserName());
		//	System.out.println(mv.getMidx());
			
			if (session.getAttribute("saveUrl") != null) { //������ �ִ� ���� url�� �ִٸ� 
				response.sendRedirect((String)session.getAttribute("saveUrl"));	//�ش��ּҷ� �̵� (String)session.getAttribute("saveUrl")
			}else {					
				response.sendRedirect(request.getContextPath()+"/board/main.do"); //���ٸ� ������������ �̵�
			}
		
		}else {				//�ƴ϶��
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");	//�α�����������			
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

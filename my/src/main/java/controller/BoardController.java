package controller;

import java.io.IOException;




import java.net.InetAddress;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.BoardVo;
import domain.SearchCriteria;
import domain.PageMaker;
import domain.QuizCriteria;
import domain.QuizPageMaker;
import srvice.BoardDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//����������
				String uri = request.getRequestURI();
				String pj = request.getContextPath();
				String command = uri.substring(pj.length());
				
				if(command.equals("/board/QuizFboard.do")) { //�����Խ��� Ŭ����
					
					String page = request.getParameter("page"); //�������� ��Ʈ�� Ÿ������ ����
					if (page == null) page ="1"; //�������� ���̸� 1
					 //��Ʈ������ ��ȯ
					int pagex= Integer.parseInt(page); //��Ʈ������ ���� �������� ��Ʈ������ ��ȯ
					String keyword = request.getParameter("keyword"); //Ű���带 ��Ʈ������ ����
					if(keyword==null) keyword="";// Ű���尡 ���̸� ""ó��
					String searchType = request.getParameter("searchType"); //�˻�����?�� ��Ʈ������ ����
					if(searchType==null) searchType=""; // ���̸� ""ó��
					
					
					SearchCriteria scri = new SearchCriteria(); //����
					scri.setPage(pagex);
					scri.setKeyword(keyword);
					scri.setSearchType(searchType);
					
					BoardDao bd = new BoardDao();
					int cnt = bd.boardTotal(scri); //������ �Ѱ����� cnt�� ����
					
					PageMaker pm =new PageMaker(); //����
					pm.setScri(scri);
					pm.setTotalCount(cnt);
					
					ArrayList<BoardVo> alist= bd.fboardSelectAll(scri);
					request.setAttribute("alist", alist); //�ڿ�����
					request.setAttribute("pm", pm);
							
					RequestDispatcher rd = request.getRequestDispatcher("/board/QuizFboard.jsp");
					rd.forward(request, response);
					
					
				}else if(command.equals("/board/Fboardwrite.do")) { //�ۼ��ϱ� Ŭ����
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Fboardwrite.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/FboardwriteAction.do")) { //�ۼ��ϱ� Ŭ����
					
				
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip �� ��������
					

					HttpSession session = request.getSession();
					
					int midx = (int)session.getAttribute("midx");
					
					BoardDao bd = new BoardDao();
					int value = bd.insertfBoard(title, content, writer, memberip, midx);
				
					
					
					if(value==1) { //�۾��� ����
						
						response.sendRedirect(request.getContextPath()+"/board/QuizFboard.do"); //�ߴٸ� index�������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardwrite.do"); //�ƴϸ� �۾��� ��������
					}
					
				}else if(command.equals("/board/Fboardcontent.do")) { //�Խñ� Ŭ��
					
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.fboardSelectOne(bidx_);
					
					ArrayList<BoardVo> clist = bd.Selectfcomment(bidx_);
					
					HttpSession session = request.getSession();
					
					if(session.getAttribute("memberid") != null) {
						int value= bd.Fboardhit(bidx_);
					}
					
					
					request.setAttribute("clist", clist);
					request.setAttribute("bv", bv);
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Fboardcontent.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/Fboardmodify.do")) { //�����ϱ� Ŭ��
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.fboardSelectOne(bidx_);
					
					
					request.setAttribute("bv", bv);

					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Fboardmodify.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/FboardmodifyAction.do")) { //�����ϱ� Ŭ��
					//System.out.println("���ϴ�");
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String bidx = request.getParameter("fbidx"); //�ϴ� String������ ����
					int bidx_ = Integer.parseInt(bidx); //bidx�� String�� Integer������, �׸��� int������ ��ȯ�Ͽ� bidx_�� ����
					
					
					
					BoardDao bd = new BoardDao();
					int value = bd.fboardmodify(title, content, writer, bidx_);
					
					
					
					
					if(value==1) { //�����ϱ� ����
						
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //�ߴٸ� ������ ���������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardmodify.do?fbidx="+bidx_); //�ƴϸ� �ۼ����ϱ� ��������
					}
					

					
				}else if(command.equals("/board/boardDeleteAction.do")) {
					
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					
					BoardDao bd = new BoardDao();
					
					int value = bd.fboardDelete(bidx_);
					
					if(value==1) { //���� ����
						
						response.sendRedirect(request.getContextPath()+"/board/QuizFboard.do"); //�ߴٸ� index�������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do"); //�ƴϸ� �۾��� ��������
					}
					
					
				}else if(command.equals("/board/QuizNboard.do")) { //�������� Ŭ��
					
					String page = request.getParameter("page"); //�������� ��Ʈ�� Ÿ������ ����
					if (page == null) page ="1"; //�������� ���̸� 1
					 //��Ʈ������ ��ȯ
					int pagex= Integer.parseInt(page); //��Ʈ������ ���� �������� ��Ʈ������ ��ȯ
					String keyword = request.getParameter("keyword"); //Ű���带 ��Ʈ������ ����
					if(keyword==null) keyword="";// Ű���尡 ���̸� ""ó��
					String searchType = request.getParameter("searchType"); //�˻�����?�� ��Ʈ������ ����
					if(searchType==null) searchType=""; // ���̸� ""ó��
					
					
					SearchCriteria scri = new SearchCriteria(); //����
					scri.setPage(pagex);
					scri.setKeyword(keyword);
					scri.setSearchType(searchType);
					
					BoardDao bd = new BoardDao();
					int cnt = bd.nboardTotal(scri); //������ �Ѱ����� cnt�� ����
					
					PageMaker pm =new PageMaker(); //����
					pm.setScri(scri);
					pm.setTotalCount(cnt);
					
					ArrayList<BoardVo> alist= bd.nboardSelectAll(scri);
					request.setAttribute("pm", pm);
					request.setAttribute("alist", alist); //�ڿ�����
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/QuizNboard.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/Nboardcontent.do")) { //�Խñ� Ŭ��
					System.out.println("���ϴ�");
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.nboardSelectOne(bidx_);
					ArrayList<BoardVo> nclist = bd.Selectncomment(bidx_);
					
					HttpSession session = request.getSession();
					
					if(session.getAttribute("memberid") != null) {
						int value= bd.Nboardhit(bidx_);
					}
					
					
					request.setAttribute("nclist", nclist);
					
					request.setAttribute("bv", bv);
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Nboardcontent.jsp");
					rd.forward(request, response);
				
				}else if(command.equals("/board/Nboardwrite.do")) { //�ۼ��ϱ� Ŭ����
		
					RequestDispatcher rd = request.getRequestDispatcher("/board/Nboardwrite.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/NboardwriteAction.do")) { //�ۼ��ϱ� Ŭ����
					
				
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip �� ��������
					

					HttpSession session = request.getSession();
					
					int midx = (int)session.getAttribute("midx");
					
					BoardDao bd = new BoardDao();
					int value = bd.insertnBoard(title, content, writer, memberip, midx);
					
					
					
					if(value==1) { //�۾��� ����
						
						response.sendRedirect(request.getContextPath()+"/board/QuizNboard.do"); //�ߴٸ� index�������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardwrite.do"); //�ƴϸ� �۾��� ��������
					}
					
				}
				
				else if(command.equals("/board/Nboardmodify.do")) { //�����ϱ� Ŭ��
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.fboardSelectOne(bidx_);
					
					
					request.setAttribute("bv", bv);

					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Nboardmodify.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/NboardmodifyAction.do")) { //�����Ϸ�
					System.out.println("���ϴ�");
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String bidx = request.getParameter("nbidx"); //�ϴ� String������ ����
					int bidx_ = Integer.parseInt(bidx); //bidx�� String�� Integer������, �׸��� int������ ��ȯ�Ͽ� bidx_�� ����
					
					
					
					BoardDao bd = new BoardDao();
					int value = bd.nboardmodify(title, content, writer, bidx_);
					
					
					
					
					if(value==1) { //�����ϱ� ����
						
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //�ߴٸ� ������ ���������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardmodify.do?nbidx="+bidx_); //�ƴϸ� �ۼ����ϱ� ��������
					}
					

					
				}else if(command.equals("/board/nboardDeleteAction.do")) {
					
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					
					
					BoardDao bd = new BoardDao();
					
					int value = bd.fboardDelete(bidx_);
					
					if(value==1) { //���� ����
						
						response.sendRedirect(request.getContextPath()+"/board/QuizNboard.do"); //�ߴٸ� index�������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //�ƴϸ� �۾��� ��������
					}
					
					
				}else if(command.equals("/board/boardreplyAction.do")) { //����ۼ�
					HttpSession session = request.getSession();
					
					String writer = (String)session.getAttribute("username"); //��� �ۼ��� �ۼ��� �Է��� ���ϹǷ� ���ǿ��� �г��� ��������
					String content = request.getParameter("content");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip �� ��������
					int midx = (int)session.getAttribute("midx");
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					BoardDao bd = new BoardDao();
					int value = bd.insertfcomment(content, writer, memberip, midx, bidx_);
					
					if(value==1) { //����ۼ� ����
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //�ߴٸ� �Խñۺ��� �������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //�����ص� �Խñۺ��� �������� �̵�
					}
					
					
				}else if(command.equals("/board/main.do")) {//����������
					//System.out.println("����");
					
					BoardDao bd = new BoardDao();
					
					ArrayList<BoardVo> mnlist = bd.nboardmain();
					ArrayList<BoardVo> mflist = bd.fboardmain();
					
					request.setAttribute("mnlist", mnlist); //�ڿ�����
					request.setAttribute("mflist", mflist); //�ڿ�����
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/main.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/Game.do")) {
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Game.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/nboardreplyAction.do")) { //����ۼ�
					HttpSession session = request.getSession();
					
					String writer = (String)session.getAttribute("username"); //��� �ۼ��� �ۼ��� �Է��� ���ϹǷ� ���ǿ��� �г��� ��������
					String content = request.getParameter("content");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip �� ��������
					int midx = (int)session.getAttribute("midx");
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String�� int������ ����
					
					BoardDao bd = new BoardDao();
					int value = bd.insertncomment(content, writer, memberip, midx, bidx_);
					
					if(value==1) { //����ۼ� ����
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //�ߴٸ� �Խñۺ��� �������� �̵�
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //�����ص� �Խñۺ��� �������� �̵�
					}
					
					
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

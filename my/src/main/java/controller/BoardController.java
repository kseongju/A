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
		
		//가상경로추출
				String uri = request.getRequestURI();
				String pj = request.getContextPath();
				String command = uri.substring(pj.length());
				
				if(command.equals("/board/QuizFboard.do")) { //자유게시판 클릭시
					
					String page = request.getParameter("page"); //페이지를 스트링 타입으로 받음
					if (page == null) page ="1"; //페이지가 널이면 1
					 //인트형으로 변환
					int pagex= Integer.parseInt(page); //스트링으로 받은 페이지를 인트형으로 변환
					String keyword = request.getParameter("keyword"); //키워드를 스트링으로 받음
					if(keyword==null) keyword="";// 키워드가 널이면 ""처리
					String searchType = request.getParameter("searchType"); //검색조건?을 스트링으로 받음
					if(searchType==null) searchType=""; // 널이면 ""처리
					
					
					SearchCriteria scri = new SearchCriteria(); //생성
					scri.setPage(pagex);
					scri.setKeyword(keyword);
					scri.setSearchType(searchType);
					
					BoardDao bd = new BoardDao();
					int cnt = bd.boardTotal(scri); //페이지 총개수를 cnt에 담음
					
					PageMaker pm =new PageMaker(); //생성
					pm.setScri(scri);
					pm.setTotalCount(cnt);
					
					ArrayList<BoardVo> alist= bd.fboardSelectAll(scri);
					request.setAttribute("alist", alist); //자원공유
					request.setAttribute("pm", pm);
							
					RequestDispatcher rd = request.getRequestDispatcher("/board/QuizFboard.jsp");
					rd.forward(request, response);
					
					
				}else if(command.equals("/board/Fboardwrite.do")) { //작성하기 클릭시
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Fboardwrite.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/FboardwriteAction.do")) { //작성하기 클릭시
					
				
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip 값 가져오기
					

					HttpSession session = request.getSession();
					
					int midx = (int)session.getAttribute("midx");
					
					BoardDao bd = new BoardDao();
					int value = bd.insertfBoard(title, content, writer, memberip, midx);
				
					
					
					if(value==1) { //글쓰기 성공
						
						response.sendRedirect(request.getContextPath()+"/board/QuizFboard.do"); //했다면 index페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardwrite.do"); //아니면 글쓰기 페이지로
					}
					
				}else if(command.equals("/board/Fboardcontent.do")) { //게시글 클릭
					
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
					
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
					
				}else if(command.equals("/board/Fboardmodify.do")) { //수정하기 클릭
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.fboardSelectOne(bidx_);
					
					
					request.setAttribute("bv", bv);

					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Fboardmodify.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/FboardmodifyAction.do")) { //수정하기 클릭
					//System.out.println("들어갑니다");
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String bidx = request.getParameter("fbidx"); //일단 String형으로 받음
					int bidx_ = Integer.parseInt(bidx); //bidx를 String을 Integer형으로, 그리고 int형으로 변환하여 bidx_로 받음
					
					
					
					BoardDao bd = new BoardDao();
					int value = bd.fboardmodify(title, content, writer, bidx_);
					
					
					
					
					if(value==1) { //수정하기 성공
						
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //했다면 수정한 글페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardmodify.do?fbidx="+bidx_); //아니면 글수정하기 페이지로
					}
					

					
				}else if(command.equals("/board/boardDeleteAction.do")) {
					
					
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
					
					BoardDao bd = new BoardDao();
					
					int value = bd.fboardDelete(bidx_);
					
					if(value==1) { //삭제 성공
						
						response.sendRedirect(request.getContextPath()+"/board/QuizFboard.do"); //했다면 index페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do"); //아니면 글쓰기 페이지로
					}
					
					
				}else if(command.equals("/board/QuizNboard.do")) { //공지사항 클릭
					
					String page = request.getParameter("page"); //페이지를 스트링 타입으로 받음
					if (page == null) page ="1"; //페이지가 널이면 1
					 //인트형으로 변환
					int pagex= Integer.parseInt(page); //스트링으로 받은 페이지를 인트형으로 변환
					String keyword = request.getParameter("keyword"); //키워드를 스트링으로 받음
					if(keyword==null) keyword="";// 키워드가 널이면 ""처리
					String searchType = request.getParameter("searchType"); //검색조건?을 스트링으로 받음
					if(searchType==null) searchType=""; // 널이면 ""처리
					
					
					SearchCriteria scri = new SearchCriteria(); //생성
					scri.setPage(pagex);
					scri.setKeyword(keyword);
					scri.setSearchType(searchType);
					
					BoardDao bd = new BoardDao();
					int cnt = bd.nboardTotal(scri); //페이지 총개수를 cnt에 담음
					
					PageMaker pm =new PageMaker(); //생성
					pm.setScri(scri);
					pm.setTotalCount(cnt);
					
					ArrayList<BoardVo> alist= bd.nboardSelectAll(scri);
					request.setAttribute("pm", pm);
					request.setAttribute("alist", alist); //자원공유
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/QuizNboard.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/Nboardcontent.do")) { //게시글 클릭
					System.out.println("들어갑니다");
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
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
				
				}else if(command.equals("/board/Nboardwrite.do")) { //작성하기 클릭시
		
					RequestDispatcher rd = request.getRequestDispatcher("/board/Nboardwrite.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/NboardwriteAction.do")) { //작성하기 클릭시
					
				
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip 값 가져오기
					

					HttpSession session = request.getSession();
					
					int midx = (int)session.getAttribute("midx");
					
					BoardDao bd = new BoardDao();
					int value = bd.insertnBoard(title, content, writer, memberip, midx);
					
					
					
					if(value==1) { //글쓰기 성공
						
						response.sendRedirect(request.getContextPath()+"/board/QuizNboard.do"); //했다면 index페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardwrite.do"); //아니면 글쓰기 페이지로
					}
					
				}
				
				else if(command.equals("/board/Nboardmodify.do")) { //수정하기 클릭
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
					
					BoardDao bd = new BoardDao();
	
					BoardVo bv = bd.fboardSelectOne(bidx_);
					
					
					request.setAttribute("bv", bv);

					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Nboardmodify.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/NboardmodifyAction.do")) { //수정완료
					System.out.println("들어갑니다");
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String bidx = request.getParameter("nbidx"); //일단 String형으로 받음
					int bidx_ = Integer.parseInt(bidx); //bidx를 String을 Integer형으로, 그리고 int형으로 변환하여 bidx_로 받음
					
					
					
					BoardDao bd = new BoardDao();
					int value = bd.nboardmodify(title, content, writer, bidx_);
					
					
					
					
					if(value==1) { //수정하기 성공
						
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //했다면 수정한 글페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardmodify.do?nbidx="+bidx_); //아니면 글수정하기 페이지로
					}
					

					
				}else if(command.equals("/board/nboardDeleteAction.do")) {
					
					
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					
					
					BoardDao bd = new BoardDao();
					
					int value = bd.fboardDelete(bidx_);
					
					if(value==1) { //삭제 성공
						
						response.sendRedirect(request.getContextPath()+"/board/QuizNboard.do"); //했다면 index페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //아니면 글쓰기 페이지로
					}
					
					
				}else if(command.equals("/board/boardreplyAction.do")) { //댓글작성
					HttpSession session = request.getSession();
					
					String writer = (String)session.getAttribute("username"); //댓글 작성시 작성자 입력을 안하므로 세션에서 닉네임 가져오기
					String content = request.getParameter("content");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip 값 가져오기
					int midx = (int)session.getAttribute("midx");
					String bidx = request.getParameter("fbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					BoardDao bd = new BoardDao();
					int value = bd.insertfcomment(content, writer, memberip, midx, bidx_);
					
					if(value==1) { //댓글작성 성공
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //했다면 게시글보기 페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Fboardcontent.do?fbidx="+bidx_); //실패해도 게시글보기 페이지로 이동
					}
					
					
				}else if(command.equals("/board/main.do")) {//메인페이지
					//System.out.println("들어옴");
					
					BoardDao bd = new BoardDao();
					
					ArrayList<BoardVo> mnlist = bd.nboardmain();
					ArrayList<BoardVo> mflist = bd.fboardmain();
					
					request.setAttribute("mnlist", mnlist); //자원공유
					request.setAttribute("mflist", mflist); //자원공유
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/main.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/Game.do")) {
					
					RequestDispatcher rd = request.getRequestDispatcher("/board/Game.jsp");
					rd.forward(request, response);
					
				}else if(command.equals("/board/nboardreplyAction.do")) { //댓글작성
					HttpSession session = request.getSession();
					
					String writer = (String)session.getAttribute("username"); //댓글 작성시 작성자 입력을 안하므로 세션에서 닉네임 가져오기
					String content = request.getParameter("content");
					String memberip = InetAddress.getLocalHost().getHostAddress(); //ip 값 가져오기
					int midx = (int)session.getAttribute("midx");
					String bidx = request.getParameter("nbidx");
					int bidx_ = Integer.parseInt(bidx); //String을 int형으로 받음
					
					BoardDao bd = new BoardDao();
					int value = bd.insertncomment(content, writer, memberip, midx, bidx_);
					
					if(value==1) { //댓글작성 성공
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //했다면 게시글보기 페이지로 이동
					}else {
						response.sendRedirect(request.getContextPath()+"/board/Nboardcontent.do?nbidx="+bidx_); //실패해도 게시글보기 페이지로 이동
					}
					
					
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

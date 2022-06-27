package edu.MVC.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.MVC.service.BoardService;
import edu.MVC.vo.BoardVo;
import edu.MVC.vo.MemberVo;

@RequestMapping(value="/Board")
@Controller
public class BoardController {
	
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="Nboard.do")
	public String Nboard(Model model, BoardVo vo) {
		
		List<BoardVo> list = boardService.selectNboard(vo);
		
		model.addAttribute("list", list);
		
		return "Board/Nboard";
	}

	@RequestMapping(value="Nboardwrite.do", method=RequestMethod.GET)
	public String Nboardwrite() {
		
		return "Board/Nboardwrite";
	}
	
	@RequestMapping(value="Nboardwrite.do", method=RequestMethod.POST)
	public void Nboardwrite(BoardVo vo,HttpServletResponse response, HttpServletRequest request, HttpSession session) throws IOException {
		
		
		
		session = request.getSession(); //세션을 요구
		MemberVo login = (MemberVo) session.getAttribute("login"); //MemberVo login이라는 객체에 세션이 가지고 있는 login이라는 속성을 넣는다.
		
		System.out.println(login.getMidx());
		vo.setMidx(login.getMidx()); //vo에 로그인안에 있는 Midx값을 담는다.
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		int result = boardService.insertNboard(vo); // result에 insertNboard실행결과를 담는다.
		
		if(result <= 0) {
			pw.append("<script>alert('글쓰기 실패.');location.href='Nboardwrite.do';</script>");
			pw.flush();
			
		}else {
			
			pw.append("<script>alert('글쓰기 성공.');location.href='Nboardcontent.do?bidx="+vo.getBidx()+"';</script>");
			pw.flush();
		}
	}
	
	@RequestMapping(value="Nboardcontent.do")
	public String Nboardcontent(int bidx,Model model) {
		
		BoardVo vo = boardService.selectoneNboard(bidx);
		model.addAttribute("vo", vo);
		
		return "Board/Nboardcontent";
	}
	
	@RequestMapping(value="delNboard.do")
	public void delNboard(int bidx,HttpServletResponse response, BoardVo vo) throws IOException {
		
		int result= boardService.delNboard(bidx);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		if(result<=0) {
			pw.append("<script>alert('삭제실패');location.href='Nboardcontent.do?bidx"+vo.getBidx()+"';</script>");
			pw.flush();
		}else {
			
			pw.append("<script>alert('삭제성공');location.href='Nboard.do';</script>");
			pw.flush();
		}
		
		
	}
	
	@RequestMapping(value="Nboardmodify.do", method=RequestMethod.GET)
	public String Nboardmodify(Model model, int bidx) {
		
		BoardVo vo= boardService.selectoneNboard(bidx);
		model.addAttribute("vo",vo);
		
		return "Board/Nboardmodify";
	}
	
	@RequestMapping(value="Nboardmodify.do", method=RequestMethod.POST)
	public void Nboardmodify(int bidx,HttpServletResponse response, BoardVo vo) throws IOException {
		
		int result= boardService.modifyNboard(vo);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		if(result <=0) {
			pw.append("<script>alert('수정실패');location.href='Nboardmodify.do?bidx="+vo.getBidx()+"';</script>");
			pw.flush();
		}else {
			pw.append("<script>alert('수정성공');location.href='Nboardcontent.do?bidx="+vo.getBidx()+"';</script>");
			pw.flush();
		}
	}
	
	
	
	@RequestMapping(value="QAboard.do", method=RequestMethod.GET)
	public String QAboard() {
		
		return "Board/QAboard";
	}
	
	
}

package edu.MVC.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.MVC.service.MemberService;
import edu.MVC.vo.MemberVo;

@RequestMapping(value="/Member")
@Controller
public class MemberController {
	
	@Autowired // 의존성주입
	MemberService memberService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET) //로그인 페이지를 거쳐 회원가입 페이지로 이동하기에 먼저 만듬
	public String MemberLogin() {
		
		return "Member/login"; //로그인화면으로 이동
	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET) //단순히 회원가입 페이지로 가는거기 때문에 get방식으로 접근
	public String Memberjoin() {
		
		return "Member/join"; //회원가입페이지로 이동
	}
	

	
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String Memberjoin(MemberVo vo, String id) {
		
		int result = memberService.idcheck(id);
		
		//다시 꺼낼때 곤란 vo.setAddr(vo.getAddr1(),vo.getAddr2(),vo.getAddr3()); //addr에 addr1,2,3을 넣어준다.
		
		if(result ==1) {
			return "/Member/join";
		}else if(result==0) {
			
			memberService.insert(vo);
		}
		
		return "redirect:/Member/login.do";
	}
	
	@ResponseBody // 무조건 반환
	@RequestMapping(value="/idcheck.do", method=RequestMethod.POST)
	public String IDcheck(String id) {
		
		//ajax를 리턴값으로 스트링 타입으로 받아야 함
		
		return memberService.idcheck(id) +""; //값을 가지고 있다 회원가입페이지에서 다시꺼냄
	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String MemberLogin(MemberVo vo, HttpSession session, HttpServletRequest request) { //session은 session을 이용할 수 있고, request는 값을 받아올 수 있음
		
		MemberVo member = memberService.selectbyLogin(vo); //member에 로그인 정보를 담는다.
	
		if(member != null) { //member가 널이 아니면
			session = request.getSession(); //세션을 생성
			MemberVo login = new MemberVo(); //login객체를 생성
			login.setId(member.getId());
			login.setPassword(member.getPassword());
			login.setName(member.getName()); //login객체 안에 로그인 정보를 담는다.
			login.setMidx(member.getMidx());
			System.out.println(login.getMidx());

			
			session.setAttribute("login", login); //login객체를 세션에 담는다. 
			
			return "redirect:/Board/Nboard.do";
		}else{
			
			return "redirect:/Member/login.do";
		}
		

	}
	
	@RequestMapping(value="/logout.do") //로그아웃
	public String MemberLogout(HttpServletRequest request, HttpSession session) {
		
		session = request.getSession(); //세션 데이터를 가져옴
		session.invalidate(); //session값을 제거
		
		return "redirect:/Member/login.do";
	}

}

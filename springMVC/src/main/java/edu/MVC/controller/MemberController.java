package edu.MVC.controller;

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
	public String Memberjoin(MemberVo vo) {
		
		int result = memberService.idcheck(vo);
		
		if(result ==1) {
			return "/Member/join.do";
		}else if(result==0) {
			
			memberService.insert(vo);
		}
		
		return "redirect:/Member/login.do";
	}
	
	@ResponseBody // 무조건 반환
	@RequestMapping(value="/idcheck.do", method=RequestMethod.POST)
	public int idcheck(MemberVo vo) {
		
		System.out.println("작동중");
		
		int result = memberService.idcheck(vo);
		
		return result; //값을 가지고 있다 회원가입페이지에서 다시꺼냄
	}

}

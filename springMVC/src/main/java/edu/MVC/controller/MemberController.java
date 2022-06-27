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
	
	@Autowired // ����������
	MemberService memberService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET) //�α��� �������� ���� ȸ������ �������� �̵��ϱ⿡ ���� ����
	public String MemberLogin() {
		
		return "Member/login"; //�α���ȭ������ �̵�
	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET) //�ܼ��� ȸ������ �������� ���°ű� ������ get������� ����
	public String Memberjoin() {
		
		return "Member/join"; //ȸ�������������� �̵�
	}
	

	
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String Memberjoin(MemberVo vo, String id) {
		
		int result = memberService.idcheck(id);
		
		//�ٽ� ������ ��� vo.setAddr(vo.getAddr1(),vo.getAddr2(),vo.getAddr3()); //addr�� addr1,2,3�� �־��ش�.
		
		if(result ==1) {
			return "/Member/join";
		}else if(result==0) {
			
			memberService.insert(vo);
		}
		
		return "redirect:/Member/login.do";
	}
	
	@ResponseBody // ������ ��ȯ
	@RequestMapping(value="/idcheck.do", method=RequestMethod.POST)
	public String IDcheck(String id) {
		
		//ajax�� ���ϰ����� ��Ʈ�� Ÿ������ �޾ƾ� ��
		
		return memberService.idcheck(id) +""; //���� ������ �ִ� ȸ���������������� �ٽò���
	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String MemberLogin(MemberVo vo, HttpSession session, HttpServletRequest request) { //session�� session�� �̿��� �� �ְ�, request�� ���� �޾ƿ� �� ����
		
		MemberVo member = memberService.selectbyLogin(vo); //member�� �α��� ������ ��´�.
	
		if(member != null) { //member�� ���� �ƴϸ�
			session = request.getSession(); //������ ����
			MemberVo login = new MemberVo(); //login��ü�� ����
			login.setId(member.getId());
			login.setPassword(member.getPassword());
			login.setName(member.getName()); //login��ü �ȿ� �α��� ������ ��´�.
			login.setMidx(member.getMidx());
			System.out.println(login.getMidx());

			
			session.setAttribute("login", login); //login��ü�� ���ǿ� ��´�. 
			
			return "redirect:/Board/Nboard.do";
		}else{
			
			return "redirect:/Member/login.do";
		}
		

	}
	
	@RequestMapping(value="/logout.do") //�α׾ƿ�
	public String MemberLogout(HttpServletRequest request, HttpSession session) {
		
		session = request.getSession(); //���� �����͸� ������
		session.invalidate(); //session���� ����
		
		return "redirect:/Member/login.do";
	}

}

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
	public String Memberjoin(MemberVo vo) {
		
		int result = memberService.idcheck(vo);
		
		if(result ==1) {
			return "/Member/join.do";
		}else if(result==0) {
			
			memberService.insert(vo);
		}
		
		return "redirect:/Member/login.do";
	}
	
	@ResponseBody // ������ ��ȯ
	@RequestMapping(value="/idcheck.do", method=RequestMethod.POST)
	public int idcheck(MemberVo vo) {
		
		System.out.println("�۵���");
		
		int result = memberService.idcheck(vo);
		
		return result; //���� ������ �ִ� ȸ���������������� �ٽò���
	}

}

package edu.MVC.service;

import edu.MVC.vo.MemberVo;

public interface MemberService {
	int insert(MemberVo vo);
	int idcheck(String id);
	MemberVo selectbyLogin(MemberVo vo);
}

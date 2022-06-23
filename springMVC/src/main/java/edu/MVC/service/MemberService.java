package edu.MVC.service;

import edu.MVC.vo.MemberVo;

public interface MemberService {
	int insert(MemberVo vo);
	int idcheck(MemberVo vo);
}

package edu.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.MVC.dao.MemberDao;
import edu.MVC.vo.MemberVo;

@Service //서비스 로직을 처리하는 곳이라는 것을 나타냄
public class MemberServiceImpl implements MemberService {

	@Autowired //의존자동주입
	MemberDao memberdao;
	
	@Override
	public int insert(MemberVo vo) {
		
		int result = memberdao.insert(vo); 
		
		return result;
	}

	@Override
	public int idcheck(MemberVo vo) {
		
		int result = memberdao.idcheck(vo);
		
		return result;
	}

}

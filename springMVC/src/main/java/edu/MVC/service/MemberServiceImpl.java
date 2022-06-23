package edu.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.MVC.dao.MemberDao;
import edu.MVC.vo.MemberVo;

@Service //���� ������ ó���ϴ� ���̶�� ���� ��Ÿ��
public class MemberServiceImpl implements MemberService {

	@Autowired //�����ڵ�����
	MemberDao memberdao;
	
	@Override
	public int insert(MemberVo vo) {
		
		int result = memberdao.insert(vo); 
		
		return result;
	}

	@Override
	public int idcheck(String id) {
		
		int result = memberdao.idcheck(id);
		
		return result;
	}

	@Override
	public MemberVo selectbyLogin(MemberVo vo) {
		
		return memberdao.selectbyLogin(vo);
	}

}

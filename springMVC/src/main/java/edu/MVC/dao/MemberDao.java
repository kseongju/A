package edu.MVC.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.MVC.vo.MemberVo;


@Repository //�����. �ܺε����Ϳ� ������ �����ϴ� ��������� ǥ��
public class MemberDao {
	
	@Autowired //�ڵ����� Ÿ������ �˻�
	SqlSession sqlSession; //Sql�� �����ϰ� ���ش�.
	
	private static final String namespace = "edu.MVC.mapper.MemberMapper"; //��θ� �ٿ��� ���� ����.

	public int insert(MemberVo vo) {
		
		return sqlSession.insert(namespace+".insert", vo);
		//���÷� ���ڸ� ���� edu.MVC.mapper.MemberMapper.insert�̴�.
	}
	
	public int idcheck(String id) {
		
		return sqlSession.selectOne(namespace+ ".idcheck", id);
	
	}
	
	public MemberVo selectbyLogin(MemberVo vo) {
		
		return sqlSession.selectOne(namespace+ ".selectbyLogin", vo);
	}
	
}

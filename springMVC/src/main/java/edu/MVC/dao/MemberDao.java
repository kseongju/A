package edu.MVC.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.MVC.vo.MemberVo;


@Repository //저장소. 외부데이터와 접촉을 많이하는 저장소임을 표시
public class MemberDao {
	
	@Autowired //자동주입 타입으로 검색
	SqlSession sqlSession; //Sql를 실행하게 해준다.
	
	private static final String namespace = "edu.MVC.mapper.MemberMapper"; //경로를 줄여서 쓰기 위함.

	public int insert(MemberVo vo) {
		
		return sqlSession.insert(namespace+".insert", vo);
		//예시로 들자면 원래 edu.MVC.mapper.MemberMapper.insert이다.
	}
	
	public int idcheck(String id) {
		
		return sqlSession.selectOne(namespace+ ".idcheck", id);
	
	}
	
	public MemberVo selectbyLogin(MemberVo vo) {
		
		return sqlSession.selectOne(namespace+ ".selectbyLogin", vo);
	}
	
}

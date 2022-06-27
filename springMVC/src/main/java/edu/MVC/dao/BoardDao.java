package edu.MVC.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import edu.MVC.vo.BoardVo;

public class BoardDao {
	
	@Autowired //�ڵ����� Ÿ������ �˻�
	SqlSession sqlSession; //Sql�� �����ϰ� ���ش�.
	
	private static final String namespace = "edu.MVC.mapper.BoardMapper";
	
		public int insertNboard(BoardVo vo) {
		
		return sqlSession.insert(namespace+".insertNboard", vo);
		//���÷� ���ڸ� ���� edu.MVC.mapper.MemberMapper.insert�̴�.
	}
		
		public List<BoardVo> selectNboard(BoardVo vo) {
			
			return sqlSession.selectList(namespace+"selectNboard",vo);
		}

}

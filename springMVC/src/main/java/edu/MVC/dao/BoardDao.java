package edu.MVC.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.MVC.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession; 
	private static final String namespace = "edu.MVC.mapper.BoardMapper";
	
		public int insertNboard(BoardVo vo) {
		
		return sqlSession.insert(namespace+".insertNboard", vo);
		
	   }
		
		public List<BoardVo> selectNboard(BoardVo vo) {
			
			return sqlSession.selectList(namespace+".selectNboard",vo);
		}
		
		public BoardVo selectoneNboard(int bidx) {
			
			return sqlSession.selectOne(namespace+".selectoneNboard", bidx);
		}
		
		public int delNboard(int bidx) {
			
			return sqlSession.update(namespace+".delNboard", bidx);
		}
		
		public int modifyNboard(BoardVo vo) {
			
			return sqlSession.update(namespace+".modifyNboard", vo);
		}

}

package edu.MVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.MVC.dao.BoardDao;
import edu.MVC.vo.BoardVo;

public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boarddao;

	@Override
	public List<BoardVo> selectNboard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}

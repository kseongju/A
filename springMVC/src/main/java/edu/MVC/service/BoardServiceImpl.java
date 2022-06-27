package edu.MVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.MVC.dao.BoardDao;
import edu.MVC.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boarddao;

	@Override
	public List<BoardVo> selectNboard(BoardVo vo) {
		
		return boarddao.selectNboard(vo);
	}

	@Override
	public int insertNboard(BoardVo vo) {
		
		return boarddao.insertNboard(vo);
	}

	@Override
	public BoardVo selectoneNboard(int bidx) {
		
		return boarddao.selectoneNboard(bidx);
	}

	@Override
	public int delNboard(int bidx) {
		
		return boarddao.delNboard(bidx);
	}

	@Override
	public int modifyNboard(BoardVo vo) {
		
		return boarddao.modifyNboard(vo);
	}

}

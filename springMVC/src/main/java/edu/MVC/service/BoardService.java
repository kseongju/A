package edu.MVC.service;

import java.util.List;

import edu.MVC.vo.BoardVo;

public interface BoardService {

	public List<BoardVo>selectNboard();
	public int insert(BoardVo vo);
}

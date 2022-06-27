package edu.MVC.service;

import java.util.List;

import edu.MVC.vo.BoardVo;


public interface BoardService {

	public List<BoardVo>selectNboard(BoardVo vo);
	public int insertNboard(BoardVo vo);
	public BoardVo selectoneNboard(int bidx);
	public int delNboard(int bidx);
	public int modifyNboard(BoardVo vo);
}

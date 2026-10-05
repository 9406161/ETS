package com.ets.mapper;

import java.util.List;

import com.ets.domain.BoardVO;
import com.ets.domain.Criteria;

public interface BoardMapper {
	public void insert(BoardVO vo)throws Exception;
	
	public BoardVO read(BoardVO vo) throws Exception;
	
	public void update(BoardVO vo)throws Exception;
	
	public void delete(BoardVO vo) throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
	public int getToTalCount(Criteria cri) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public void updateCnt(BoardVO vo) throws Exception;
	
	public int cntreply(BoardVO vo)throws Exception;
}

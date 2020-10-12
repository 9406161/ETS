package com.ets.mapper;

import java.util.List;

import com.ets.domain.MerBoardVO;
import com.ets.domain.Criteria;

public interface MerBoardMapper {
	public void insert(MerBoardVO vo)throws Exception;
	
	public MerBoardVO read(MerBoardVO vo) throws Exception;
	
	public void update(MerBoardVO vo)throws Exception;
	
	public void delete(MerBoardVO vo) throws Exception;
	
	public List<MerBoardVO> listPage(Criteria cri) throws Exception;
	
	public int getToTalCount(Criteria cri) throws Exception;
	
	public List<MerBoardVO> listAll() throws Exception;
	
	public void updateCnt(MerBoardVO vo) throws Exception;

}

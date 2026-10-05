package com.ets.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ets.domain.Criteria;
import com.ets.domain.MerBoardVO;
import com.ets.mapper.MerBoardMapper;

@Service
public class MerBoardServiceImpl implements MerBoardService {
	@Inject
	private MerBoardMapper bomapper;
	
	
	// 게시판 글 작성
	@Transactional
	@Override
	public void insert(MerBoardVO vo)throws Exception{
		bomapper.insert(vo);
	}

	@Transactional
	@Override
	public MerBoardVO read(MerBoardVO vo) throws Exception {
		bomapper.updateCnt(vo);
		return bomapper.read(vo);
	}
	@Override
	public void update(MerBoardVO vo) throws Exception {
		bomapper.update(vo);
	}
	
	@Transactional
	@Override
	public void delete(MerBoardVO vo) throws Exception {
		bomapper.delete(vo);
	}
	@Override
	public List<MerBoardVO> listPage(Criteria cri) throws Exception {
		return bomapper.listPage(cri);
	}
	@Override
	public int getToTalCount(Criteria cri) throws Exception {
		return bomapper.getToTalCount(cri);
	}
	@Override
	public List<MerBoardVO> listAll() throws Exception {
		
		return bomapper.listAll();
	}
	@Override
	public void updateCnt(MerBoardVO vo) throws Exception {
		bomapper.updateCnt(vo);
	}

}

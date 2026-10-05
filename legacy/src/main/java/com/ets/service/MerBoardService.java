package com.ets.service;

import java.util.List;

import com.ets.domain.Criteria;
import com.ets.domain.MerBoardVO;

public interface MerBoardService {
	// 게시판 글 작성
	public void insert(MerBoardVO vo)throws Exception;

	/*
	 * // 게시글 리스트 public List<BoardVO> list() throws Exception;
	 */
	// 게시물 보기
	public MerBoardVO read(MerBoardVO vo) throws Exception;
	// 게시물 수정
	public void update(MerBoardVO vo)throws Exception;
	// 게시물 삭제
	public void delete(MerBoardVO vo) throws Exception;
	
	public List<MerBoardVO> listAll() throws Exception;
	
	public List<MerBoardVO> listPage(Criteria cri) throws Exception;
	
	public int getToTalCount(Criteria cri) throws Exception;
	
	public void updateCnt(MerBoardVO vo) throws Exception;
	
}

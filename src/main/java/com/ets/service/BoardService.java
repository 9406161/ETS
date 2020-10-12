package com.ets.service;

import java.util.List;

import com.ets.domain.Criteria;
import com.ets.domain.BoardAttachVO;
import com.ets.domain.BoardVO;

public interface BoardService {
	// 게시판 글 작성
	public void insert(BoardVO vo)throws Exception;
	// 게시물 보기
	public BoardVO read(BoardVO vo) throws Exception;
	// 게시물 수정
	public void update(BoardVO vo)throws Exception;
	// 게시물 삭제
	public void delete(BoardVO vo) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
	public int getToTalCount(Criteria cri) throws Exception;
	
	public void updateCnt(BoardVO vo) throws Exception;
	
	public List<BoardAttachVO> getAttachList(int bno) throws Exception;
	
	public int cntreply(BoardVO vo)throws Exception;

}

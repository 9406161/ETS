package com.ets.service;



import com.ets.domain.Criteria;
import com.ets.domain.ReplyPageDTO;
import com.ets.domain.ReplyVO;

public interface ReplyService {
	// 댓글 리스트
	public ReplyPageDTO list (int bno,Criteria cri) throws Exception;
	// 현재 게시물의 댓글 수
	public int getCountByBno(int bno) throws Exception;
	// 댓글 쓰기
	public void create(ReplyVO vo) throws Exception;
	// 댓글 수정
	public void update(ReplyVO vo) throws Exception;
	// 댓글 삭제
	public void delete(int rno) throws Exception;
	
}

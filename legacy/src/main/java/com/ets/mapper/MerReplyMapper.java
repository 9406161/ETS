package com.ets.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ets.domain.Criteria;
import com.ets.domain.MerReplyVO;

public interface MerReplyMapper {
	// 댓글 리스트 보기
	public List<MerReplyVO> list(@Param("bno") int bno, @Param("cri")Criteria cri) throws Exception;
	// 현재 게시물 댓글 수
	public int getCountByBno(int bno)throws Exception;
	// 댓글 쓰기
	public void create(MerReplyVO vo) throws Exception;
	// 댓글 수정
	public void update(MerReplyVO vo) throws Exception;
	// 댓글 삭제
	public void delete(int rno) throws Exception;
}

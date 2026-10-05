package com.ets.mapper;

import java.util.List;

import com.ets.domain.BoardAttachVO;
import com.ets.domain.BoardVO;

public interface BoardAttachMapper {
	// 파일 업로드를 etb_attach테이블에  insert
	public void ainsert(BoardAttachVO vo) throws Exception;
	// 파일 업로드를 etb_attach테이블에 delete
	public void delete(BoardVO vo)throws Exception;
	// 파일 업로드를 etb_attach테이블에 select
	public List<BoardAttachVO> select(int bno)throws Exception;
	
	public void update(BoardAttachVO vo)throws Exception;
}
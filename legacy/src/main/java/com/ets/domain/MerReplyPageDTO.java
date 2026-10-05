package com.ets.domain;

import java.util.List;

public class MerReplyPageDTO {
	// list
	private List<MerReplyVO> list;
	// count
	private int replycnt;
	
	public MerReplyPageDTO(List<MerReplyVO> list, int replycnt) {
		this.list=list;
		this.replycnt=replycnt;
	}

	public List<MerReplyVO> getList() {
		return list;
	}

	public void setList(List<MerReplyVO> list) {
		this.list = list;
	}

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	@Override
	public String toString() {
		return "MerReplyPageDTO [list=" + list + ", replycnt=" + replycnt + "]";
	}
	
}

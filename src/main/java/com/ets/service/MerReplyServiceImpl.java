package com.ets.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.domain.Criteria;
import com.ets.domain.MerReplyPageDTO;
import com.ets.domain.MerReplyVO;
import com.ets.mapper.MerReplyMapper;

@Service
public class MerReplyServiceImpl implements MerReplyService {
	
	@Autowired
	private MerReplyMapper remapper;
	
	@Override
	public MerReplyPageDTO list(int bno, Criteria cri) throws Exception {
		return new MerReplyPageDTO(remapper.list(bno, cri),remapper.getCountByBno(bno));
	}

	@Override
	public int getCountByBno(int bno) throws Exception {
		return remapper.getCountByBno(bno);
	}

	@Override
	public void create(MerReplyVO vo) throws Exception {
		remapper.create(vo);
	}

	@Override
	public void update(MerReplyVO vo) throws Exception {
		remapper.update(vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		remapper.delete(rno);
	}

}

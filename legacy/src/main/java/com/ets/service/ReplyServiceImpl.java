package com.ets.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.domain.Criteria;
import com.ets.domain.ReplyPageDTO;
import com.ets.domain.ReplyVO;
import com.ets.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper remapper;
	
	@Override
	public ReplyPageDTO list(int bno, Criteria cri) throws Exception {
		return new ReplyPageDTO(remapper.list(bno, cri),remapper.getCountByBno(bno));
	}

	@Override
	public int getCountByBno(int bno) throws Exception {
		return remapper.getCountByBno(bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		remapper.create(vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		remapper.update(vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		remapper.delete(rno);
	}

}

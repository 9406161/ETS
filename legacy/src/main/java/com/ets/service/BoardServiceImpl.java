package com.ets.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ets.domain.BoardAttachVO;
import com.ets.domain.BoardVO;
import com.ets.domain.Criteria;
import com.ets.mapper.BoardAttachMapper;
import com.ets.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardMapper bomapper;
	@Inject
	private BoardAttachMapper attachmapper;
	
	// 게시판 글 작성
	@Transactional
	@Override
	public void insert(BoardVO vo) throws Exception{
		if(vo.getAttachList()==null) {
			bomapper.insert(vo);			
		}else {
			bomapper.insert(vo);			
			vo.getAttachList().forEach(attach->{
				attach.setBno(vo.getBno());
				try {
					attachmapper.ainsert(attach);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});			
		}
	}

	@Transactional
	@Override
	public BoardVO read(BoardVO vo) throws Exception {
		bomapper.updateCnt(vo);
		return bomapper.read(vo);
	}
	@Override
	public void update(BoardVO vo) throws Exception {
		//attachmapper.update(vo);
		bomapper.update(vo);
	}
	
	@Transactional
	@Override
	public void delete(BoardVO vo) throws Exception {
		bomapper.delete(vo);
		attachmapper.delete(vo);
	}
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		
		return bomapper.listPage(cri);
	}
	@Override
	public int getToTalCount(Criteria cri) throws Exception {
		return bomapper.getToTalCount(cri);
	}
	@Override
	public List<BoardVO> listAll() throws Exception {
		
		return bomapper.listAll();
	}
	@Override
	public void updateCnt(BoardVO vo) throws Exception {
		bomapper.updateCnt(vo);
	}
	@Override
	public List<BoardAttachVO> getAttachList(int bno) throws Exception {
		return attachmapper.select(bno);
	}

	@Override
	public int cntreply(BoardVO vo) throws Exception {
		return bomapper.cntreply(vo);
	}
	
}

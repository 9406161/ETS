package com.ets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ets.domain.Criteria;
import com.ets.domain.MerReplyPageDTO;
import com.ets.domain.MerReplyVO;
import com.ets.service.MerReplyService;

@RestController
@RequestMapping("merreplies")
public class MerReplyController {

	@Autowired
	private MerReplyService reservice;
	
	@RequestMapping(value="/all/{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<MerReplyPageDTO> list(@PathVariable("bno") int bno, @PathVariable("page") int page) throws Exception{
		System.out.println("bno = " +bno);
		Criteria cri = new Criteria(page,10);
		
		return new ResponseEntity<>(reservice.list(bno, cri),HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody MerReplyVO vo) throws Exception{
		ResponseEntity<String> entity=null;
		try {
			reservice.create(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);			
		}
		return entity;
	}
	
	@RequestMapping(value="/update/{rno}", method=RequestMethod.PUT)
	public ResponseEntity<String> update (@PathVariable("rno") int rno , @RequestBody MerReplyVO vo) throws Exception{
		ResponseEntity<String> entity=null;
		try {
			vo.setRno(rno);
			reservice.update(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/delete/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete (@PathVariable("rno") int rno) throws Exception{
		ResponseEntity<String> entity=null;
		try {
			reservice.delete(rno);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
		
}

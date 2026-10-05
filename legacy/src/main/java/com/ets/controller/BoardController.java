package com.ets.controller;






import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ets.domain.BoardAttachVO;

import com.ets.domain.PageDTO;
import com.ets.domain.Criteria;

import com.ets.controller.BoardController;
import com.ets.domain.BoardVO;
import com.ets.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boservice;
	//-----------------------------------------------------------------------------------------------------
	//--게시글 작성 화면
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void writeGet() throws Exception{
		logger.info("write get...");		
	}
	//--게시글 작성
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String insert(BoardVO vo,Model model) throws Exception{
		logger.info("write post...");
		logger.info(" BoardVO에 저장 되어있는 값 : "+vo);
		boservice.insert(vo);
		model.addAttribute("result","success");
		return "redirect:/board/list"; 
	}
	//-----------------------------------------------------------------------------------------------------
	
	//--게시물 목록
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model,@ModelAttribute("cri") Criteria cri, BoardVO vo) throws Exception{
		logger.info("list get...");
		model.addAttribute("list",boservice.listPage(cri));
		model.addAttribute("pageMaker",new PageDTO(cri,boservice.getToTalCount(cri)));
		return "board/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	//--게시글 조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(BoardVO vo,@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info("read get..."+vo+cri);
		
		model.addAttribute("read",boservice.read(vo));
	}
	@RequestMapping(value="getAttachList",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> getAttachList(int bno) throws Exception{
		logger.info("getAttachList" + bno);
		
		return new ResponseEntity<>(boservice.getAttachList(bno),HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------------

	//--게시글 수정
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateGet(BoardVO vo,@ModelAttribute("cri") Criteria cri,Model model) throws Exception{
		logger.info("update get..."+ boservice.read(vo));
		model.addAttribute("update",boservice.read(vo));

	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updatePost(BoardVO vo,Criteria cri,RedirectAttributes rttr) throws Exception{
		logger.info("update post...");
		boservice.update(vo);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");

		return "redirect:/board/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	//-- 게시글 삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(BoardVO vo,Criteria cri,RedirectAttributes rttr) throws Exception{
		logger.info("delete..." + vo);
		boservice.delete(vo);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addFlashAttribute("msg","REMOVE");
		
		return "redirect:/board/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	
	
	//--
	
	//-----------------------------------------------------------------------------------------------------
	

}

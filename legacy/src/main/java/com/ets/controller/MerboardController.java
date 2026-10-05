package com.ets.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ets.domain.Criteria;
import com.ets.domain.MerBoardVO;
import com.ets.domain.PageDTO;
import com.ets.service.MerBoardService;

@Controller
@RequestMapping("merboard")
public class MerboardController {
private static final Logger logger = LoggerFactory.getLogger(MerboardController.class);
	
	@Autowired
	private MerBoardService boservice;
	
	//-----------------------------------------------------------------------------------------------------
	//--게시글 작성 화면
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void writeGet() throws Exception{
		logger.info("write get...");		
	}
	//--게시글 작성
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String insert(MerBoardVO vo) throws Exception{
		logger.info("write post...");
		logger.info("MerBoardVO에 저장 되어있는 값 : "+vo);
		boservice.insert(vo);
		//model.addAttribute("result","success");
		return "redirect:/merboard/list"; 
	}
	//-----------------------------------------------------------------------------------------------------
	
	//--게시물 목록
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model,@ModelAttribute("cri") Criteria cri ) throws Exception{
		logger.info("list get...");
		model.addAttribute("list",boservice.listPage(cri));
		model.addAttribute("pageMaker",new PageDTO(cri,boservice.getToTalCount(cri)));
		//	model.addAttribute("reply",reservice.getReplycnt());
		return "merboard/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	//--게시글 조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(MerBoardVO vo,@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		logger.info("read get..."+vo+cri);
		
		model.addAttribute("read",boservice.read(vo));
	}
	@RequestMapping(value="read",method = RequestMethod.POST)
	public MerBoardVO readPost(MerBoardVO vo) throws Exception{
		logger.info("read Post" + vo);
		boservice.read(vo);
		return vo;
	}
	//-----------------------------------------------------------------------------------------------------

	//--게시글 수정
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateGet(MerBoardVO vo,@ModelAttribute("cri") Criteria cri,Model model) throws Exception{
		logger.info("update get..."+ boservice.read(vo));
		model.addAttribute("update",boservice.read(vo));

	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updatePost(MerBoardVO vo,Criteria cri,RedirectAttributes rttr) throws Exception{
		logger.info("update post...");
		boservice.update(vo);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addFlashAttribute("msg","SUCCESS");

		return "redirect:/merboard/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	//-- 게시글 삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(MerBoardVO vo,Criteria cri,RedirectAttributes rttr) throws Exception{
		logger.info("delete..." + vo);
		boservice.delete(vo);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addFlashAttribute("msg","REMOVE");
		
		return "redirect:/merboard/list";
	}
	//-----------------------------------------------------------------------------------------------------
	
	
	
	//--
	
	//-----------------------------------------------------------------------------------------------------
	

}

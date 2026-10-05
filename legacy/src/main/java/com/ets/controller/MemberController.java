package com.ets.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ets.controller.MemberController;
import com.ets.domain.EmailSender;
import com.ets.domain.MailVO;
import com.ets.domain.MemberVO;
import com.ets.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	

	@Autowired
	private MemberService meservice; 
	
	//--회원가입
	@RequestMapping(value="/member",method = RequestMethod.GET)
	public void memberGet()throws Exception{
		logger.info("member get...");
	}
	@RequestMapping(value="/member",method = RequestMethod.POST)
	public String memberPost(MemberVO vo)throws Exception{
		meservice.createMember(vo);
		logger.info("memberjoin : "+vo);
		return "redirect:/member/login";
	}
	//-----------------------------------------------------------------------------------------------------
	//--로그인
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public void loginGet()throws Exception{
		logger.info("login get...");
	}
	
	@RequestMapping(value="/loginPost",method = RequestMethod.POST)
	public String loginPost(MemberVO vo, HttpServletRequest request)throws Exception{
		logger.info("login post...");
		HttpSession session = request.getSession();
		MemberVO member = meservice.login(vo);
		if(member!=null) {
			session.setAttribute("member", member);	
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}
	//-----------------------------------------------------------------------------------------------------
	//--아이디 찾기
	@RequestMapping(value="/findid",method = RequestMethod.GET)
	public void findidGet() throws Exception {
		logger.info("findid get...");		
	}
	
	@RequestMapping(value="/findid",method = RequestMethod.POST)
	public String findidPost(MemberVO vo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = meservice.findid(vo);
		logger.info("findid : " + member);
		if(member!=null) {
			session.setAttribute("find", member);
			return "redirect:/member/findid";
		}else {			
			return "redirect:/member/findid";
		}
	}
	//-----------------------------------------------------------------------------------------------------
	//--로그아웃
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("logout...");
		session.invalidate();
		
		return "redirect:/";
	}
	//-----------------------------------------------------------------------------------------------------
	//--비밀번호 찾기
	@RequestMapping(value="/findpw",method = RequestMethod.GET)
	public void findpwGet() throws Exception{
		logger.info("findpw get...");
	}
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private MailVO email;
	@RequestMapping(value="/findpw",method = RequestMethod.POST)
	public ModelAndView findpwPost(@RequestParam Map<String,Object> paramMap, ModelMap model) throws Exception{
		ModelAndView mav;
		String id = (String)paramMap.get("userid");
		String e_mail = (String)paramMap.get("email");
		String pw = meservice.getPw(paramMap);
		logger.info("id : " + id + ", pw : " + pw + "e_mail : " + e_mail+ " / email : " + email);
		if(pw!=null) {
			email.setContent("비밀번호는 " + pw + "입니다");
			email.setReceiver(e_mail);
			email.setSubject(id+"님 비밀번호 찾기 메일 입니다.");
			emailSender.SendEmail(email);
			mav = new ModelAndView("redirect:/member/login");
			return mav;
		}else {
			mav = new ModelAndView("redirect:/member/findpw");
			return mav;
		}
	}
	//-----------------------------------------------------------------------------------------------------
	//--회원정보 수정
	@RequestMapping(value="/modify",method = RequestMethod.GET)
	public void modifyGet(MemberVO vo,Model model) throws Exception{
		model.addAttribute("member",meservice.login(vo));
	}
	@RequestMapping(value="/modify",method = RequestMethod.POST)
	public String modifyPost(MemberVO vo, RedirectAttributes rttr)throws Exception{
		logger.info("modify post..."+vo);
		meservice.memberUpdate(vo);
		return "redirect:/member/login";
	}
	//-----------------------------------------------------------------------------------------------------
	//--아이디 중복체크
	@RequestMapping(value="/idCheck/{userid}",method = RequestMethod.GET)
	public ResponseEntity<Integer> idCheck(@PathVariable("userid") String userid) throws Exception{
		int result = meservice.idCheck(userid);
		logger.info("userid = "+userid+" / result = "+result);
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	//--이메일 중복체크
	@RequestMapping(value="/emailCheck/{email:.+}",method = RequestMethod.GET)
	public ResponseEntity<Integer> emailCheck(@PathVariable("email") String email) throws Exception{
		int result = meservice.emailCheck(email);
		logger.info("useremail = "+email +"/ result = " +result);
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------------
	//--회원탈퇴
	@RequestMapping(value="/memdelete",method = RequestMethod.GET)
	public void memberDeleteGet() throws Exception{
		logger.info("deleteGet...");
	}
	@RequestMapping(value="/memdelete",method = RequestMethod.POST)
	public String memberDeletePost(MemberVO vo, HttpSession session, RedirectAttributes rttr)throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("member");
		String sessionPass = member.getUserpw();
		String voPass = vo.getUserpw();
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg",false);
			return "redirect:/member/memdelete";
		}
		meservice.memberDelete(vo);
		session.invalidate();
		logger.info("deletePost..."+vo);
		return "redirect:/member/login";
	}
	

}

package com.ets.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.domain.MemberVO;
import com.ets.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memapper;
	
	@Override
	public void createMember(MemberVO vo) throws Exception {
		memapper.createMember(vo);
	}
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		MemberVO member = memapper.login(vo);
		return member;
	}
	@Override
	public MemberVO findid(MemberVO vo) throws Exception {
		MemberVO member = memapper.findid(vo);
		return member;
	}
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		 memapper.memberUpdate(vo);

	}
	@Override
	public int idCheck(String userid) throws Exception {
		int result=memapper.idCheck(userid);
		return result;
	}
	@Override
	public int emailCheck(String email) throws Exception {
		int result=memapper.emailCheck(email);
		return result;
	}
	@Override
	public String getPw(Map<String, Object> paramMap) throws Exception{

		return memapper.getPw(paramMap);
	}
	public void memberDelete(MemberVO vo)throws Exception{
		memapper.memberDelete(vo);
	}
}

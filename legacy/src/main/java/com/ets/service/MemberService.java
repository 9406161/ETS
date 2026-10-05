package com.ets.service;


import java.util.Map;

import com.ets.domain.MemberVO;

public interface MemberService {
	// 회원가입
	public void createMember(MemberVO vo) throws Exception;
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;
	// ID찾기
	public MemberVO findid(MemberVO vo) throws Exception;
	// 회원정보 수정
	public void memberUpdate(MemberVO vo)throws Exception;
	// 회원 탈퇴
	public void memberDelete(MemberVO vo)throws Exception;
	// 아이디 중복 체크
	public int idCheck(String userid)throws Exception;
	// 이메일 중복 체크
	public int emailCheck(String email)throws Exception;
	// 비밀번호 이메일
	public String getPw(Map<String, Object> paramMap)throws Exception;
}

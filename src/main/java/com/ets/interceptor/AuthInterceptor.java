package com.ets.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//세션 설정
		HttpSession session =request.getSession();
		logger.info("session: " + session);
		//session에 로그인 정보가 없으면 (=로그인을 하지 않았으면) --> 글쓰기 권한이 없으므로 로그인을 할 수 있는 페이지로 이동
		if(session.getAttribute("member")==null) {
			// 글쓰기 권한이 없으므로 로그인을 할 수 있는 페이지로 이동.
			response.sendRedirect("/ETS/member/login");
			return false;
		}
		// 로그인을 했으면, 글쓰기 권한을 준다
		return true;
	}






}

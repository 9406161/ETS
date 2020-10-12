package com.ets.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	//private static final String LOGIN = "login";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView)throws Exception{
		System.out.println("post handle......"); //컨트롤러 실행 후
		//MemberController에 loginPost 메소드에 얻은 select 결과의 model정보를 interceptor해서 session영역에 저장
		// session을 사용하겠다고 선언
		HttpSession session =request.getSession();
		Object userVO = modelAndView.getModel().get("userVO");
		// 세션 유지시간
		// session.setMaxInactiveInterval(60*30);// 세션을 30분동안 유지
		// userVO에 정보가 있으면
		if(userVO!=null) {
			// 세션에 userVO값 저장 
			logger.info("new login success");
			session.setAttribute("login", userVO);
			//if(request.getParameter("useCookie") != null){
				Cookie loginCookie = new Cookie("loginCookie",session.getId());//쿠키 생성
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*7); // 초 *분*시*일 = 일주일 쿠기저장
				response.addCookie(loginCookie);
			//}
			
			response.sendRedirect("/ETS"); //메인으로 가라.
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		System.out.println("pre handle......");//컨트롤러 실행 전
		
		return true;
	}

}

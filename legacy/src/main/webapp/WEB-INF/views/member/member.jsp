<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBERJOIN</title>
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../resources/js/member.js"></script>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/member.css">
</head>
<body id="body">
	<header>
		<div id="header">
			<!-- 로고 -->
			<h1 id="logo">
				<a href="/ETS"><img src="../resources/img/logo2.png"></a>
			</h1>

			<!--utile-->
			<div id="utile">
				<ul>
					<li><a href="#">공지사항</a></li>
					<li><a href="#">고객센터</a></li>
					<li><a href="#">이용안내</a></li>
					<c:choose>
						<c:when test="${member.userid==null}">
							<li><a href="/ETS/member/login">로그인</a></li>						
						</c:when>
						<c:otherwise>
							<li><a href="/ETS/member/logout">로그아웃</a></li>						
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</header>
<section id="formmember">
	<form action="/ETS/member/member" method="post" onsubmit="return checkmem()">
	<fieldset>
	
			<div>
				<label>아이디</label> <br>
				<input type="text" name="userid" id="userid" placeholder="">
				<label id="idmsg">4~12자 이내로 영문/숫자로 입력하세요</label> 
			</div>
			
		<div class="divwr">
			<div id="pw">
				<label>비밀번호</label> <br>
				<input type="password" name="userpw" id="userpw" placeholder=""><br>
				<label id="pwmsg">특수문자를 포함하여 <br>8자 이상 입력하세요</label>	
			</div>
			<div id="repw">
				<label>비밀번호 재확인</label> <br>
				<input type="password" name="re_userpw" id="re_userpw" placeholder=""><br>
				<label id="repwmsg"></label>
				 				
			</div>
		</div>		
		
			<div>
				<label>이름</label> <br>
				<input type="text" name="username" id="username" placeholder="">
			</div>
			<div>
				<label>이메일</label> <br>
				<input type="text" name="email" id="email" placeholder=""><br>
				<label id="emailmsg"></label> 
			</div>
			<div>
				<label>핸드폰</label> <br>
				<input type="text" name="phone" id="phone"placeholder=""><br>
			</div>
			<div>
				<label>팀 이름</label> <br>
				<input type="text" name="teamname" id="teamname"placeholder=""><br><br>
			</div>
			<div>
				<input type="submit" value="회원가입" id="submit">
			</div>
</fieldset>
</form>

</section>

	<footer>
	<hr>
		<div id="footerwr">
			<div>
			
			
			</div>

			<div id="footerutile">
				<ul>
					<li><a href="#">회사 소개</a></li>
					<li><a href="#">이용약관</a></li>
					<li><a href="#">광고안내</a></li>
					<li><a href="#">개인정보 처리방침</a></li>
					<li><a href="#">청소년 보호정책</a></li>
				</ul>
			</div>
		</div>
	</footer>


</body>
</html>
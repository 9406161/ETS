<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>회원탈퇴</title>

<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/memdelete.css">
</head>
<body>
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
<section id="findwr">
	<div id="fwr">
		<a href="/ETS/member/memdelete" id="fid">회원탈퇴</a>
	</div>
	<div id="findform">
		<form action="/ETS/member/memdelete" method="post">
			<input type="text" id="userid" name="userid" value="${member.userid}" readonly="readonly"> <br><br>
			<input type="text" id="username" name="username" value="${member.username}" readonly="readonly"><br><br>
			<input type="password" id="userpw" name="userpw" placeholder="패스워드"> <br><br>
			<input type="submit" id="submit" value="확인">
		</form>
	</div>
</section>	
	</body>
	
</html>
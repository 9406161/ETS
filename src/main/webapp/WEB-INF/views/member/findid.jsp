<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID찾기</title>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/findid.css">
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
				<a href="/ETS/member/findid" id="fid">아이디찾기</a>
				<a href="/ETS/member/findpw" id="fpw">비밀번호찾기</a>
			</div>
			<div id="findform">
				<form action="/ETS/member/findid" method="post">
					<input type="text" id="username" name="username" placeholder="이름"><br><br>
					<input type="text" id="email" name="email" placeholder="이메일"><br><br>
					<input type="submit" id="submit" value="확인">
				</form>
			</div>
			
			<div id="idval">
			<h1>아이디</h1> <br>
				<input type="text" value="${find.userid}" id="val" readonly>
			</div>
	</section>
</body>
</html>
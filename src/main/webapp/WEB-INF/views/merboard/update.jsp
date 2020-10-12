<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/update.css">
</head>
<body>
<header>
		<div id="header">
			<!-- 로고 -->
			<h1 id="logo">
				<a href="/ETS"><img src="../resources/img/logo2.png"></a>
			</h1>

			<!--헤더중앙-->
			<div id="cheader">

				<h5>${member.teamname}팀</h5>

			</div>

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

	<!--  -->
	<div id="gnb">
		<div id="gnb_1">
			<nav>
				<ul>
					<li><a href="/ETS/">홈</a></li>
					<li><a href="/ETS/board/list">자유게시판</a></li>
					<li><a href="/ETS/merboard/list">매치게시판</a></li>
					<li><a href="#">랭킹</a></li>
				</ul>
			</nav>

			<div id="gnbdiv">
				<c:choose>
					<c:when test="${member.userid==null}">
						<a href="member/login">로그인해주세요!</a>
					</c:when>
					<c:otherwise>
						<h5>${member.username}님반갑습니다.</h5>
						<a href="/ETS/member/modify">회원정보수정</a>
						<a>|</a>
						<a href="/ETS/member/logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
<div id="main">
	<div id="writewr">
		<form role="form" action="/ETS/merboard/update" method="post">
			<input type="hidden" name="bno" value="${update.bno}"><br>
			<input type="text" name="writer" value="${update.writer}" id="writer" readonly><br><br>
			<input type="text" name="title" value="${update.title}" id="title" maxlength="50"> <br><br>
			<p>음란물, 차별, 비하, 혐오 및 초상권, 저작권 침해 게시물은 민, 형사상의 책임을 질 수 있습니다. [저작권법 안내] [게시물 활용 안내]</p><br>
			<textarea name="content" >${update.content}</textarea><br><br>
			
			<button type="button" id="list" class="btn" onClick="ETS/merboard/list">목록</button>
			<input type="submit" value="저장" id="submit" class="btn">
		</form>
	</div>
</div>
</body>
</html>
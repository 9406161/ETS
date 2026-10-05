<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/modify.css">
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../resources/js/modify.js"></script>
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
		<form action="/ETS/member/modify" method="post"  onsubmit="return checkmem()" role="form">
			<fieldset>
				<div>
					<label>아이디</label> <br> <input type="text" name="userid"
						id="userid" value="${member.userid}" readonly="readonly">
				</div>
				<br>

				<div class="divwr">
					<div id="pw">
						<label>비밀번호</label> <br> <input type="password" name="userpw"
							id="userpw"><br> <label id="pwmsg"></label>
						<label id="pwmsg"></label> 
					</div>
					<div id="repw">
						<label>비밀번호 재확인</label> <br> <input type="password"
							name="re_userpw" id="re_userpw"><br> <label
							id="repwmsg"></label>
						<label id="repwmsg"></label> 
					</div>
				</div>
				<br>

				<div>
					<label>이름</label> <br> <input type="text" name="username"
						id="username" value="${member.username}">
				</div>
				<br>
				<div>
					<label>이메일</label> <br> <input type="text" name="email"
						id="email" value="${member.email}"><br>
					<label id="emailmsg"></label> 
				</div>
				<br>
				<div>
					<label>핸드폰</label> <br> <input type="text" name="phone"
						id="phone" value="${member.phone}"><br>
				</div>
				<br>
				<div>
					<label>팀 이름</label> <br> <input type="text" name="teamname"
						id="teamname" value="${member.teamname}"><br>
					<label id="teammsg"></label> 
				</div>
				<br>
				<div>
					<input type="submit" value="수정하기" id="submit"> <br> <br>
					<a href="/ETS/member/memdelete" id="del">회원탈퇴</a>
				</div>
			</fieldset>
		</form>
	</section>

</body>
</html>
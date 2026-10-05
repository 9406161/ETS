<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../resources/js/read.js"></script>
<script type="text/javascript" src="../resources/js/reply.js"></script>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/read.css">
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
						<a href="/ETS/member/login">로그인해주세요!</a>
					</c:when>
					<c:otherwise>
						<h5>${member.username} 님</h5>
						<a href="/ETS/member/modify">회원정보수정</a>
						<a>|</a>
						<a href="/ETS/member/logout">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<section>
		<div>
			<h2>
				<a href="list">자유게시판</a>
			</h2>
		</div>
		<hr class="hr1">
		<br>
		<form role="form">
			<input type="hidden" name="bno" id="bno" value="${read.bno}">
			<input type="hidden" name="pageNum" value="${cri.pageNum}">
			<div>
				<h3>
					<span>${read.title}</span>
				</h3>
			</div>
			<br>
			<div id="wrr">
				<div>
					<span>${read.writer}</span><span id="date"><fmt:formatDate
							pattern="yyyy.MM.dd HH:mm:ss" value="${read.regdate}" /></span>
				</div>
				<div>
					<span>조회 ${read.viewcnt}</span>
				</div>
			</div>
			<hr>
			<div id="content">
				<pre> ${read.content}</pre>
				<div class="uploadResult">
					<ul>

					</ul>
				</div>
			</div>

			<hr class="hr2">
			<div id="re_1">
				<div id="re_2">

					<ul id="replies"></ul>
					<div id="repg">
						<ul id="replyPage"></ul>
					</div>
				</div>
				<div id="reply">
					<div>
						<input type="text" name="replyer" id="newReplyWriter"
							value="${member.userid}" readonly="readonly" placeholder="로그인 후 작성 가능">
					</div>
					<div>
					<c:choose>
						<c:when test="${member.userid==null}">
							<input type="text" name="replytext" id="newReplyText" readonly="readonly">
						</c:when>
						<c:otherwise>
							<input type="text" name="replytext" id="newReplyText">
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${member.userid==null}">
						</c:when>
						<c:otherwise>
							<button id="replyAddBtn">등록</button>
						</c:otherwise>
					</c:choose>
					</div>
				</div>

			</div>

			<div id="btndiv">
				<div>
					<button type="button" id="list-btn" class="btn">목록</button>
				</div>
				<div>
					<c:choose>
						<c:when test="${member.userid==read.writer}">
							<button type="submit" id="update-btn" class="btn">수정</button>
							<button type="submit" id="delete-btn" class="btn">삭제</button>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<button type="submit" id="write-btn" class="btn">글쓰기</button>
				</div>
			</div>
		</form>
		<input type="hidden" value="${member.userid}" id="user">
	</section>
</body>
</html>
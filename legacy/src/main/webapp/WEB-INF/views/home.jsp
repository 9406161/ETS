<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="resources/css/reset.css">
<link rel="stylesheet" href="resources/css/home.css">
</head>
<body>
	<div>
		<header>
			<div id="header">
				<!-- 로고 -->
				<h1 id="logo">
					<a href="/ETS"><img src="resources/img/logo2.png"></a>
				</h1>

				<!--헤더중앙-->
				<div id="cheader"></div>

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
						<li><a href="board/list">자유게시판</a></li>
						<li><a href="merboard/list">매치게시판</a></li>
						<li><a href="#">랭킹</a></li>
					</ul>
				</nav>

				<div id="gnbdiv">
					<c:choose>
						<c:when test="${member.userid==null}">
							<a href="member/login">로그인해주세요!</a>
						</c:when>
						<c:otherwise>
							<h5>${member.username}님</h5>
							<a href="/ETS/member/modify">회원정보수정</a>
							<a>|</a>
							<a href="/ETS/member/logout">로그아웃</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!--  -->
		<div id="main">
			<div id="rank_m">2020/4시즌 팀 순위</div>
			<div id=rank>
				<div id="first">
					<div class=cfirst>
						<h1>1위 DT</h1>
						<br>
						<div>
							<img src="resources/img/rank1-1.jpg">
						</div>
					</div>
				</div>

				<div id=second>
					<div class="csecond">
						<h1>2위 하늘아 덤벼라</h1>
						<br>
						<div>
							<img src="resources/img/rank1.jpg">
						</div>
						<br>
					</div>
					<div class="csecond">
						<h1>3위 양산유나이티드</h1>
						<br>
						<div>
							<img src="resources/img/rank4.jpg">
						</div>
						<br>

					</div>
				</div>

				<div id="third">
					<div class="cthird">
						<h1>4위 슛돌이</h1>
						<br>
						<div>
							<img src="resources/img/1.jpg">
						</div>
						<br>
					</div>
					<div class="cthird">
						<h1>5위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
					<div class="cthird">
						<h1>6위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
				</div>

				<div id="fourth">
					<div class="cfourth">
						<h1>7위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
					<div class="cfourth">
						<h1>8위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
					<div class="cfourth">
						<h1>9위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
					<div class="cfourth">
						<h1>10위 ---</h1>
						<br>
						<div>
							<img src="resources/img/rank1M.png">
						</div>
						<br>
					</div>
				</div>
			</div>
		</div>

	</div>
	<footer>
		<hr>
		<div id="footerwr">
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
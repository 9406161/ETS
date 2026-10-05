<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물</title>
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../resources/js/list.js"></script>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/list.css">
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
							<h5>${member.username} 님 </h5>
							<a href="/ETS/member/modify">회원정보수정</a>
							<a>|</a>
							<a href="/ETS/member/logout">로그아웃</a>			
						</c:otherwise>
					</c:choose>
			</div>
		</div>
	</div>
<section>
	<form role="form">
		<table>
			<tr>
				<th>번호</th>
				<th>제 목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회</th>
				<!-- <th></th> -->
			</tr>
			<c:forEach items="${list}" var="board">
			<tr>
				<td id="bno">${board.bno}</td>
				<td id="title"><a href="/ETS/board/read?bno=${board.bno}&pageNum=${pageMaker.cri.pageNum}">${board.title}</a></td>
				<td id="writer">${board.writer}</td>
				<td id="regdate"><fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd"/></td>
				<td id="viewcnt">${board.viewcnt}</td>
				<!-- <td id="cnt"></td> -->
			</tr>
			</c:forEach>
		</table>
			<div id="write">
				<input type="button" value="글쓰기" id="write-btn">
			</div>
	</form>
	
		<div id="paging">
			<c:if test="${pageMaker.prev}">
				<a href="/ETS/board/list?pageNum=${pageMaker.startPage-1}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">이전</a>
			</c:if>
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<a href="/ETS/board/list?pageNum=${num}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">${num}</a>
			</c:forEach>
			<c:if test="${pageMaker.next}">
				<a href="/ETS/board/list?pageNum=${pageMaker.endPage+1}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">다음</a>
			</c:if>
		</div>
		<div id="search">
			<form action="/ETS/board/list" method="get">
					<select name="type">
						<option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':'' }"/>>제목</option>
						<option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':'' }"/>>내용</option>
						<option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':'' }"/>>작성자</option>
						<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':'' }"/>>제목+내용</option>
						<option value="TCW" <c:out value="${pageMaker.cri.type eq 'TCW'?'selected':'' }"/>>제목+내용+작성자</option>
					</select> 
						<input type="text" name="keyword" id="keyword"> <input type="submit"value="검색" id="search-btn">
			</form>
		</div>
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
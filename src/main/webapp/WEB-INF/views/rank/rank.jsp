<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기록/순위</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>순위</th>
		</tr>

		str+="
		<li data-rno='"+this.rno+"' data-replyer='"+this.replyer+"'
			class='replyLi'><div class='rpwr'>
				<div class='replyer'>"+this.replyer+"</div>
				<div class='replytext'>"+ this.replytext +"</div>
				<div class='regdate'>
					<span>"+this.regdate+"</span>
					<button>X</button>
				</div>
			</div></li>";

	</table>
</body>
</html>
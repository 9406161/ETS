<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script type="text/javascript" src="../resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").on("click",function(){
		var id = $("#userid").val();
		var pw = $("#userpw").val();
		if(id==""){
			alert("아이디를 입력해주세요.");
		}else if(pw==""){
			alert("비밀번호를 입력해주세요.")
		}
	});
});

</script>
<link rel="stylesheet" href="../resources/css/reset.css">
<link rel="stylesheet" href="../resources/css/login.css">
</head>
<body id="body">
    <section id="login">
		<h1 id="logo">
			<a href="/ETS"><img src="../resources/img/logo2.png"></a>
		</h1>
    </section>
    <section id="formlogin">
        <form action="/ETS/member/loginPost" method="post">
            <table id="logintable">
                <tr>
                    <td><h2> <br> </h2></td> 
                </tr>
                <tr>
                    <td><h2> <br> </h2></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="userid" id="userid" placeholder="아이디">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="userpw" id="userpw" placeholder="비밀번호">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="로그인" id="submit">
                    </td>
                </tr>
                <tr>
                	<td>
                	<hr>
                	</td>
                </tr>
                <tr>
                	<td>
                		<a href="/ETS/member/findid">아이디가 생각나지 않으세요?<br></a> 
                	</td>
                </tr> 
                <tr>
                	<td>
						<a href="/ETS/member/member">가입하세요!</a>
                	</td>
                </tr>
                </table>
        </form>
   </section>
</body>
</html>
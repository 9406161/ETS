var idreg;
var idVal;
var pwreg;
var pwVal;
var repwVal;
var userid;
var teamname;
var email;
var idcheck=false;
var pwcheck=false;
var cidcheck=false;
var repwcheck=false;
var cemail=false;

$(document).ready(function(){
	
	$("#userpw").on("blur",function(){
		var pwreg = /^(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var pwVal = $("#userpw").val();
		
		if(pwreg.test(pwVal)){
			$("#pwmsg").html("사용가능한 비밀번호 입니다.");
			pwcheck=true;
		}else{
			$("#pwmsg").html("문자/특수문자 포함 <br>8자이상 입력하세요.");	
			pwcheck=false;
		}
	});
	$("#re_userpw").on("blur",function(){
		pwVal = $("#userpw").val();
		repwVal = $("#re_userpw").val();
		
		if(repwVal==pwVal){
			$("#repwmsg").html("O");
			repwcheck=true;
		}else{
			$("#repwmsg").html("X");
			repwcheck=false;
		}
	});
/*		$("#email").on("blur",function(){
		email = $("#email").val();
		chemail = $("#cemail").val();
		$.getJSON("/ETS/member/emailCheck/"+email,function(data){
			console.log(chemail);
			if(chemail==email){
				$("#emailmsg").html("O");
			}else{
				if(data==1){
					$("#emailmsg").html("중복된 이메일 입니다.");
					cemail=false;
				}else{
					$("#emailmsg").html("O");
					cemail=true;
				}
				cemail=true;
			}
		});
	});*/
});

function checkmem(){
	if(pwcheck&&repwcheck){
		return true;
	}else if(pwcheck==false){
		alert("비밀번호를 확인해주세요")
		return false;					
	}else if(repwcheck==false){
		alert("비밀번호가 일치하지 않습니다.")
		return false;					
	}/*else if(cemail==false){
		alert("이메일을 확인해주세요")
		return false;
}*/
	
}
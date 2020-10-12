
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
	$("#userid").on("blur",function(){
		idreg = /^[a-zA-Z0-9]{4,12}$/;
		idVal = $("#userid").val();
		
		if(idreg.test(idVal)){
			$("#idmsg").html("사용하실수 있는 아이디 입니다.");
			idcheck=true;
		}else{
			$("#idmsg").html("4~12자 이내로 영문/숫자로 입력하세요.");
			idcheck=false;
		}
	});
	
	$("#userpw").on("blur",function(){
		var pwreg = /^(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var pwVal = $("#userpw").val();
		
		if(pwreg.test(pwVal)){
			$("#pwmsg").html("사용하실수 있는 비밀번호 입니다.");
			pwcheck=true;
		}else{
			$("#pwmsg").html("문자/특수문자 포함  8자이상 입력하세요.");	
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
			$("#repwmsg").html("비밀번호가 틀립니다.");
			repwcheck=false;
		}
	});
	
	$("#userid").on("blur",function(){
		userid = $("#userid").val();
		$.getJSON("/ETS/member/idCheck/"+userid,function(data){
			console.log(data);
			if(data==1){
				$("#idmsg").html("중복된 아이디 입니다.");
				cidcheck=false;
			}else{
				$("#idmsg").html("O");
				cidcheck=true;
			}
		});
	});
/*		$("#email").on("blur",function(){
		email = $("#email").val();
		$.getJSON("/ETS/member/emailCheck/"+email,function(data){
			console.log(data);
			if(data==1){
				alert("aaa");
				$("#emailmsg").html("중복된 이메일 입니다.");
				cemail=false;
			}else{
				$("#emailmsg").html("사용가능한 이메일 입니다.");
				cemail=true;
			}
		});
	});*/
	
	
});

function checkmem(){
	if(idcheck&&pwcheck&&cidcheck&&repwcheck){
		return true;
	}else if(idcheck==false){
		alert("아이디를 확인해주세요")
		return false;
	}else if(pwcheck==false){
		alert("비밀번호를 확인해주세요")
		return false;		
	}else if(cidcheck==false){
		alert("아이디가 중복되었습니다.")
		return false;				
	}else if(repwcheck==false){
		alert("비밀번호가 일치하지 않습니다.")
		return false;					
	}/*else if(cemail==false){
		alert("이메일을 확인해주세요")
		return false;
	}*/
	
}
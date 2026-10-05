$(document).ready(function(){
	var bno = $("#bno").val();
	var page = 1;
	getAllList();
	// 댓글 리스트
	function getAllList(){
		var str = "";
		$.getJSON("/ETS/merreplies/all/"+bno+"/"+page, function(data){
			console.log(data);
			$(data.list).each(
				function(){
					str+="<li data-rno='"+this.rno+"'data-replyer='"+this.replyer+"' class='replyLi'><div class='rpwr'><div class='replyer'>"+this.replyer+"</div><div class='replytext'>"+ this.replytext +"</div><div class='regdate'><span>"+this.regdate+"</span><button  data-rno='"+this.rno+"'data-replyer='"+this.replyer+"'>X</button></div></div></li>";
					});
			
			var endNum=Math.ceil(page/10.0)*10;
			var startNum=endNum-9;
			var prev=startNum>1;
			var next=false;
			
			if(endNum*10>=data.replycnt){
				endNum=Math.ceil(data.replycnt/10.0);
			}
			if(endNum*10<data.replycnt){
				next=true;
			}
			
			var pagestr="";
			
			if(prev){
				pagestr+="<li><a href='"+(startNum-1)+"'>이전</a></li>";
			}
			
			for(var i = startNum; i <endNum; i++){
				var active = page==i?"active":"";
				pagestr+="<li class='page-item"+active+" '><a href='"+i+"'>"+i+"</a></li>";
			}
			if(next){
				pagestr+="<li><a href='"+(endNum+1)+"'>다음</a></li>";
			}
			
			$("#replyPage").html(pagestr);
			$("#replies").html(str);		
		});
	}
	$("#replyPage").on("click","li a", function(e){
		e.preventDefault();
		var targetPageNum = $(this).attr("href");
		page=targetPageNum;
		getAllList(page);
	});
	
	
	// 댓글 등록
	$("#replyAddBtn").on("click",function(){
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();
		if(replytext!=""){
			$.ajax({
				type:"post",
				url:"/ETS/merreplies",
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override":"POST"},
				dataType:"text",
				data:JSON.stringify({bno:bno, replyer:replyer, replytext:replytext}),
				async:false,
				success:function(result){
					if(result="SUCCESS"){
						alert("등록되었습니다.");
						$("#newReplyText").val(" ");
						getAllList(page);
					}
				}
			});
		}else{
			alert("내용을 입력해 주세요.");
		}
		
	});
// ----------------------------------------------------------------	

	$('#replies').on("click",".replyLi button",function(){		
		var rno = $(this).data("rno"); // rno 값을 가져온다
		var replyer = $(this).data("replyer"); // rno 값을 가져온다
		var member = $("#user").val();
		console.log(replyer);
		console.log(member);
		if(replyer==member){
			$.ajax({
				type:"delete",
				url:"/ETS/merreplies/delete/"+rno,
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override":"DELETE"},
				dataType:"text",
				success:function(result){
					console.log("result: " + result);
					if(result=="SUCCESS"){
						alert("삭제되었습니다.");
						console.log(result);
						getAllList(page);
					}	
				}
			});
		}else if(replyer!=member){
			alert("권한이 없습니다.");
		}
			
	});
	
	
	
	
	
	
	
});
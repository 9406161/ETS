$(document).ready(function(){

	var form = $("form[role='form']");
	
	// 수정
	$("#update-btn").on("click",function(){
		form.attr("action","/ETS/board/update");
		form.attr("method","get");
		form.submit();
	});
	// 삭제
	$("#delete-btn").on("click",function(){
		form.attr("action","/ETS/board/delete");
		form.attr("method","post");
		form.submit();
	});
	// 글쓰기
	$("#write-btn").on("click",function(){
		form.attr("action","/ETS/board/write");
		form.attr("method","get");
		form.submit();
	});
	// 목록
	$("#list-btn").on("click",function(){
		form.attr("action","/ETS/board/list");
		form.attr("method","get");
		form.submit();
	});	
	
	
	//bno값을 저장하는 변수
	var bno = $('#bno').val();
	
	// tbl_attachList에 대한 처리
	$.getJSON("/ETS/board/getAttachList",{bno:bno},function(arr){
		console.log(arr);
		var str="";
		$(arr).each(function(i,attach){
			var fileCallPath = encodeURIComponent(attach.uploadPath+"/"+attach.uuid+"_"+attach.fileName);
			if(attach.fileType){ // 이미지타입
				var sfileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);		
				str+= "<li><img src='/ETS/display?fileName="+fileCallPath+"'>"+"</li>";
			}else{// 그외 타입
				str+= "<li><a href='/ETS/download?fileName="+fileCallPath+"'>"+"<img src='resources/img/캡처.PNG'>"+attach.fileName+"</a></li>";
			}
		});
		$(".uploadResult ul").append(str);
	});
	
});// ready
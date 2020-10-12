/**
 * 
 */
$(document).ready(function(){

	var form = $("form[role='form']");
	
	// 수정
	$("#update-btn").on("click",function(){
		form.attr("action","/ETS/merboard/update");
		form.attr("method","get");
		form.submit();
	});
	// 삭제
	$("#delete-btn").on("click",function(){
		form.attr("action","/ETS/merboard/delete");
		form.attr("method","post");
		form.submit();
	});
	// 글쓰기
	$("#write-btn").on("click",function(){
		form.attr("action","/ETS/merboard/write");
		form.attr("method","get");
		form.submit();
	});
	// 목록
	$("#list-btn").on("click",function(){
		form.attr("action","/ETS/merboard/list");
		form.attr("method","get");
		form.submit();
	});	
});// ready
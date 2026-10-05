/**
 * 
 */
$(document).ready(function(){
	var form = $("form[role='form']");
	// 목록
	$("#write-btn").on("click",function(){
		form.attr("action","/ETS/board/write");
		form.attr("method","get");
		form.submit();
	});
	
});// ready	

$(document).ready(function(){
	// 정규식
	var regex = new RegExp("(.*?)\.(exe|sh|zip|akz)$"); // 정규식(해당 종류의 파일은 업로드 할수 없게)
	// 파일 사이즈
	var maxSize = 5242880; // 파일 사이즈(5MB) 1024(KB) * 1024(KB) * 5
	
	function checkExtension(fileName, fileSize){
		
		if(fileSize>=maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}

	
	$(".fileDrop").on("dragenter dragover",function(e){
		e.preventDefault();
	});
	
	$(".fileDrop").on("drop",function(e){
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;
		console.log(files);
		var formData = new FormData(); // uploadAjax.jsp에 Form태그가없기 떄문에 대체할 FormData(); 작성
		for(var i=0;i<files.length;i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("file",files[i]);
		}
		
		$.ajax({
			url:"/ETS/uploadAjax",
			data:formData,
			dataType:"json",
			// processData,contentType은 파일업로드시 false가 되어야 전송된다.
			processData:false,
			contentType:false,
			type:"post",
			success: function(data){
				console.log(data);
				alert("업로드 됨")
			var str = "";
			//data는 배열이기 떄문에 for문같은 반복문을 이용해서 0~ 배열끝까지 화면에 출력(each)
				$(data).each(function(i,obj){
					if(!obj.image){ // 이미지 파일이 아니면
						var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
						str+= "<li data-filename='"+obj.fileName+"' data-uuid='"+obj.uuid+"' data-uploadpath='"+obj.uploadPath+"' data-fileType='"+obj.image+"'><a href='/ETS/download?fileName="+fileCallPath+"'>"+obj.fileName+"</a>"+"<span data-file='"+fileCallPath+"' data-type='image'>삭제</span>"+"</li>";				
					}else{ // 이미지 파일이면
						var sfileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
						var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
						str+= "<li data-filename='"+obj.fileName+"' data-uuid='"+obj.uuid+"' data-uploadpath='"+obj.uploadPath+"' data-fileType='"+obj.image+"'><img src='/ETS/display?fileName="+fileCallPath+"'>"+"<span data-file='"+sfileCallPath+"' data-type='image'>삭제</span>"+"</li>";
					}
				})
				$(".uploadResult ul").append(str);

			}
		
		});
	});//img drop end 
	
	$(".uploadResult").on("click","span",function(e){
		//파일명 변수, 이미지 파일여부 변수
		var fileName = $(this).data("file");
		var type=$(this).data("type");
		var par = $(this).parent();
		console.log(par);
		$.ajax({	
			url:"/ETS/deleteFile",
			data:{fileName:fileName,type:type},//어떤 파일을 삭제해야 하는지(파일명),이미지 파일 인지 아닌지
			dataType:"text",
			type:"POST",
			success:function(data){
				if(data=="deleted"){
				par.remove();					
				alert("삭제 되었습니다.");
				}
			}
		})
	});//X 
		
	$("#isub").on("click",function(){
		var title = $("#title").val();
		var content = $("#content").val();
		if(title==""){
			alert("제목을 입력해주세요.");
			return false;
		}else if(content==""){
			alert("내용을 입력해주세요.")
			return false;
		}else{
				// form태그 선택자를 이용해서 form 태그를 선택하여 formObj변수에 저장  form태그에 submit속성울 선택
			var formObj=$("form[role='form']");
			/*$("input[type='submit']").on("click",function(e){*/ //input태그에 role속성이 form인것을 선택
				//e.preventDefault();
				alert("전송 완료");
				var str="";
				$(".uploadResult ul li").each(function(i,obj){
					
					var jobj=$(obj);
					console.log(obj);
					str+="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
					str+="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
					str+="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("uploadpath")+"'>";
					str+="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("filetype")+"'>";
				});
				// formObj에 str을 추가하여 submit
				formObj.append(str).submit();
				//console.log(obj);
			/*});*/
		}
	});
	


});
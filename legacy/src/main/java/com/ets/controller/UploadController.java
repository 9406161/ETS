package com.ets.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.ets.domain.AttachFileDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller

public class UploadController {
	
	//@Resource(name="uploadPath")
	//private String uploadPath;
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	
	@RequestMapping(value="uploadForm",method=RequestMethod.GET)
	public void uploadForm() {
		logger.info("파일 업로드 화면..."); // 글쓰기를 위한 화면 폼
	}
	
	@RequestMapping(value="uploadForm",method=RequestMethod.POST)
	public void uploadForm(MultipartFile[] file, Model model)throws Exception {
		String uploadPath = "C:\\uploadFolder";
		for(MultipartFile multipartFile : file) {	
			
			logger.info("originalName: " + multipartFile.getOriginalFilename()); // 실제 파일명
			logger.info("size: " + multipartFile.getSize()); // 파일 크기
			logger.info("contentType: " + multipartFile.getContentType()); // 이미지 / 한글 / 엑셀 ...
			logger.info("uploadPath: " + uploadPath); // 파일 저장 위치
			
			File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			}
	}
	
	@RequestMapping(value="uploadAjax",method=RequestMethod.GET)
	public void uploadAjax() {
		logger.info("파일 업로드 화면");
	}
	
	
	
	//----------------------------------------------------------------------------
	private String getFolder() {
	// 년/월/일 폴더를 생성하는 getFolder()메서드
	// 년/월/일 (날짜), 오늘 날짜를 어떻게 구할것인가
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();//오늘 날짜를 date 변수에 저장
	String str = sdf.format(date);
	//System.out.println("오늘날짜 : "+str);
	logger.info("오늘 날짜: "+str); //str = 2020\08\25
	return str.replace("-", File.separator);
	}
	
	// 이미지 파일을 판단할 수 있게 하는 메서드
	private boolean checkImageType(File file) {
		try {
			String contentType=Files.probeContentType(file.toPath());
			return contentType.startsWith("image") ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	//----------------------------------------------------------------------------
	
	@RequestMapping(value="uploadAjax",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] file) throws Exception {
		logger.info("파일업로드 Ajax처리");
		logger.info("파일의 길이" + file.length);
		String uploadPath = "C:\\uploadFolder";
		
		// AttachFileDTO 클래스를 list배열로 생성
		List<AttachFileDTO> list = new ArrayList<>();
		
		//-------------------------------------------------------------
		// 파일을 만들어서 관리 할 떄
 		File uploadFolder = new File(uploadPath,getFolder()); 
		logger.info("파일 업로드 폴더: " + uploadFolder);
		// 년 월 일 폴더 만들기
		// exists() 메서드를 활용하여 생성하고자 하는 폴더가 존재하지 않으면 폴더를 만들어라
		if(uploadFolder.exists()==false) {
			uploadFolder.mkdirs(); // 폴더를 만들어주는 메서드
		} // make yyyy\MM\dd 형식으로 만들어진다
		//-------------------------------------------------------------
		
		for(MultipartFile multipartFile : file) {			
			logger.info("파일 명: " + multipartFile.getOriginalFilename());
			logger.info("파일 크기: " + multipartFile.getSize()); 
			logger.info("파일 종류 : " + multipartFile.getContentType()); 
			//logger.info("파일 저장 위치: " + uploadPath);
			
			AttachFileDTO attach = new AttachFileDTO();
			String fileName = multipartFile.getOriginalFilename();	
			attach.setFileName(fileName);
			
			//-----------------------------------------------------
			//UUID를 이용해서 중복파일 이름 다르게 하기
			String uploadFileName=multipartFile.getOriginalFilename();

			UUID uuid=UUID.randomUUID();
			
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			//-----------------------------------------------------
			
			// 기본 방식
			// File saveFile = new File(uploadPath, multipartFile.getOriginalFilename()); 폴더 관리 전

			// 폴더 관리
			/*File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());*/ // 중복 불가
			//File saveFile = new File(uploadFolder, uploadFileName); //�ߺ�����
			
			try {
				
				File saveFile = new File(uploadFolder, uploadFileName); // 중복 가능
				multipartFile.transferTo(saveFile); // transferTo 폴더에 파일을 저장
				attach.setUploadPath(getFolder());
				attach.setUuid(uuid.toString());
				
				// 파일이 이미지 파일이면 썸네일을 만들어서 저장
				// 그냥 파일이면 그냥 저장
				if(checkImageType(saveFile)) {
					// 업로드 된 파일이 이미지라면
					attach.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadFolder,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				list.add(attach);
				
				//multipartFile.transferTo(saveFile); // transferTo 폴더에 파일을 저장
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			}
		return new ResponseEntity<List<AttachFileDTO>>(list,HttpStatus.OK);
		//return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		
	}
	
	//display ( 업로드파일이 이미지 인것 )
	@RequestMapping(value="display",method=RequestMethod.GET) // localhost:8080/ETS/display?파일저장위치/파일명
	public ResponseEntity<byte[]> getFile(String fileName) {
		logger.info("fileName: "+ fileName);
		File file = new File("C:\\uploadFolder\\"+fileName);
		logger.info("file: "+ file);
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type",Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//download(업로드 파일이 이미지가 아닌것)
	@RequestMapping(value="download",method=RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> dowmloadFile(String fileName) {
		//fileName=fileName.substring(fileName.indexOf("_")+1);
		logger.info("download file : " + fileName);
		Resource resource = new FileSystemResource("C:\\uploadFolder\\"+fileName);
		logger.info("resource : " + resource);
		String resourceName = resource.getFilename();
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Disposition","attachment; filename=\""+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
		
	}
	
	//파일삭제 Start
	@RequestMapping(value="deleteFile",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName,String type, HttpServletRequest requeset){
		logger.info("fileName : "+fileName);
		logger.info("type: "+type);
		File file;
		
		try {
			file = new File("C:\\uploadFolder\\"+URLDecoder.decode(fileName,"UTF-8"));
			//썸네일 이미지 삭제
			file.delete();
			if(type.equals("image")) {
				String originalFile=file.getAbsolutePath().replace("s_", "");
				file = new File(originalFile);
				//원본 이미지 삭제
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("deleted",HttpStatus.OK);
	}
	
}

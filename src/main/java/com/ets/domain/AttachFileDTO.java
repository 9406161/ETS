package com.ets.domain;

public class AttachFileDTO {
	// 파일이름(문자열)
	private String fileName;
	// 파일 저장 위치(문자열)
	private String uploadPath;	
	// UUID (문자열)
	private String uuid;
	// image여부(true/false) : true면 이미지파일-display false면 이미지 아닌파일 -download
	private boolean image;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "AttachFileDTO [fileName=" + fileName + ", uploadPath=" + uploadPath + ", uuid=" + uuid + ", image="
				+ image + "]";
	}
}

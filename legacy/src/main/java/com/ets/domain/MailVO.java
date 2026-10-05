package com.ets.domain;

public class MailVO {
	private String subject;
	private String content;
	private String receiver;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	@Override
	public String toString() {
		return "MailVO [subject=" + subject + ", content=" + content + ", receiver=" + receiver + "]";
	}
	

}

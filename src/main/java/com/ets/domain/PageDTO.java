package com.ets.domain;

public class PageDTO {
	//
	private int startPage;
	
	//
	private int endPage; 
	
	//
	private boolean prev,next;

//	private int displayPageNum=10;
	
	//
	private int total;
	private Criteria cri;
	
	//startPage, endPage 
	public PageDTO(Criteria cri, int total) {
		this.cri = cri; 
		this.total=total;
		// endPage 
		this.endPage=(int)(Math.ceil(cri.getPageNum()/10.0))*10;
		// startPage 
		this.startPage=this.endPage-9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if(realEnd<endPage) {
			this.endPage=realEnd;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage < realEnd;
		
	
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
}

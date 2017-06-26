package com.multi.contacts.biz.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class RESTResult {
	private String status;
	private String message;
	private int no;
	
	public RESTResult() {
	}
	
	public RESTResult(String status, String message, int no) {
		super();
		this.status = status;
		this.message = message;
		this.no = no;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}

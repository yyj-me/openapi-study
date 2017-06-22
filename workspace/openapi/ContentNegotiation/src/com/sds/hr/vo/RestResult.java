package com.sds.hr.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResult {
	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public RestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestResult(boolean result) {
		super();
		this.result = result;
	} 
	
	
}

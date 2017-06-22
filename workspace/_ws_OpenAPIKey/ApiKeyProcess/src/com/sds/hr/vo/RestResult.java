package com.sds.hr.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResult {
	private boolean result;
	private String errorMessage;
	private CountryList countryList;
	
	public CountryList getCountryList() {
		return countryList;
	}

	public void setCountryList(CountryList countryList) {
		this.countryList = countryList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public RestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestResult(boolean result, String errorMessage, CountryList countryList) {
		super();
		this.result = result;
		this.errorMessage = errorMessage;
		this.countryList = countryList;
	} 
	
	
}

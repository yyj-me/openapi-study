package com.tfactory.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.multipart.MultipartFile;


@XmlRootElement
public class ModelVO implements Serializable {


	private static final long serialVersionUID = 1L;

	private String mcode;
	private String mname;
	private String mdesc;
	private String regDate;
	private String mimg;
	

	
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMdesc() {
		return mdesc;
	}
	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "ModelVO [mcode=" + mcode + ", mname=" + mname + ", mdesc="
				+ mdesc + ", regDate=" + regDate + ", mimg=" + mimg + "]";
	}
	
	
}

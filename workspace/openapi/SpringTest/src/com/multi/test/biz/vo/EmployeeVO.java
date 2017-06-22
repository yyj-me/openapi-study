package com.multi.test.biz.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	 
    @XmlAttribute
    private Integer id;
    private String name;
    private String email;
    
	public EmployeeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeVO(Integer id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
	      return "EmployeeVO [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}

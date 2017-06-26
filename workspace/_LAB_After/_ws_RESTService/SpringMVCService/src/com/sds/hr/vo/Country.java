package com.sds.hr.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="country")
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
	
	@XmlAttribute(name="country_id")
	private String country_id;
	private String country_name;
	private String region_name;
	
	public Country() {
	}
	
	public Country(String country_id, String country_name, String region_name) {
		this.country_id = country_id;
		this.country_name = country_name;
		this.region_name = region_name;
	}

	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
}

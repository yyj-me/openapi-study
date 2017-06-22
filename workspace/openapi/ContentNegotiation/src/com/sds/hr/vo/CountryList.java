package com.sds.hr.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="countrylist")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryList {
	
	@XmlElement
	private int count;
	@XmlElement(name="country")
	private List<Country> countries;
	
	public CountryList() {

	}
	
	public CountryList(int count, List<Country> countries) {
		this.count = count;
		this.countries = countries;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Country> getCountries() {
		return countries;
	}
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
}

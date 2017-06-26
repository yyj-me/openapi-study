package com.sds.hr.service;

import java.util.List;

import com.sds.hr.vo.Country;

public interface CountryService {
	List<Country> getAllCountries() throws Exception;
	Country getCountry(String country_id)  throws Exception;
	void updateCountry(Country country)  throws Exception;
}

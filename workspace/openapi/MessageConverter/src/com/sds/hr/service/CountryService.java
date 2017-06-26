package com.sds.hr.service;

import com.sds.hr.vo.Country;
import com.sds.hr.vo.CountryList;

public interface CountryService {
	CountryList getAllCountries() throws Exception;
	Country getCountry(String country_id)  throws Exception;
	void updateCountry(Country country)  throws Exception;
}

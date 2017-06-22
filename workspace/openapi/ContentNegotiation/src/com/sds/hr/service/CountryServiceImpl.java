package com.sds.hr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sds.hr.dao.CountryDAO;
import com.sds.hr.vo.Country;
import com.sds.hr.vo.CountryList;

@Service("CountryService")
public class CountryServiceImpl implements CountryService {
	
	@Resource(name="CountryDAO")
	private CountryDAO countryDAO;
	
	@Override
	public CountryList getAllCountries() throws Exception {
		List<Country> countries = countryDAO.getAllCountries();
		CountryList list = new CountryList(countries.size(), countries);
		return list;
	}

	@Override
	public Country getCountry(String country_id) throws Exception {
		return countryDAO.getCountry(country_id);
	}

	@Override
	public void updateCountry(Country country)  throws Exception{
		countryDAO.updateCountry(country);
	}

}

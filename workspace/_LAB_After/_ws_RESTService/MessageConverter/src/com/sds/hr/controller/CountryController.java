package com.sds.hr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.hr.service.CountryService;
import com.sds.hr.vo.Country;
import com.sds.hr.vo.CountryList;
import com.sds.hr.vo.RestResult;

@Controller
public class CountryController {
	private static Logger logger = Logger.getLogger(CountryController.class);
	
	@Autowired
	private CountryService service;
	
	@RequestMapping(method=RequestMethod.GET, value="/countries")
	public @ResponseBody CountryList getCountries()throws Exception{
		logger.info("getAllCountries" );
		CountryList list = service.getAllCountries();
		return list;
	}

	@RequestMapping(method=RequestMethod.GET, value="/countries/{id}")
	public @ResponseBody Country getCountry( 
			@PathVariable("id") String country_id) throws Exception{
		
		logger.info("getCountry");
		return service.getCountry(country_id);
	}

	//JSON으로 업데이트할 때는 다음과 같은 형식으로..
	//{"country_id":"AR","country_name":"Argentina","region_name":"Americas"}
	@RequestMapping(method=RequestMethod.PUT, value="/countries/{id}")
	public @ResponseBody RestResult updateCountry(
			@PathVariable("id") String country_id, 
			@RequestBody Country country) throws Exception {

		logger.info("updateCountry");
		RestResult result = new RestResult();
		if (country_id.equals(country.getCountry_id())) {
			service.updateCountry(country);
			result.setResult(true);
		} else {
			result.setResult(false);
		}
		return result;
	}
	
}

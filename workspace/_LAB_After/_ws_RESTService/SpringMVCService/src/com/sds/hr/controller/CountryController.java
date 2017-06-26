package com.sds.hr.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sds.hr.common.CommonUtil;
import com.sds.hr.service.CountryService;
import com.sds.hr.vo.Country;
import com.sds.hr.vo.CountryList;
import com.sds.hr.vo.RestResult;

@Controller
public class CountryController {
	private static Logger logger = Logger.getLogger(CountryController.class);
	
	@Resource(name="jsonTemplate")
	private View jsonView;
	
	@Autowired
	@Qualifier("xmlTemplate")
	private View xmlView;
	
	@Autowired
	private CountryService service;
	

	@RequestMapping(method=RequestMethod.GET, value="/countries.json")
	public ModelAndView getCountriesJSON()throws Exception{
		
		logger.info("getAllCountrieJSON" );
		
		ModelAndView mav = new ModelAndView();
		CountryList list = service.getAllCountries();
		mav.addObject(list);
		mav.setView(jsonView);
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countries.xml")
	public ModelAndView getCountriesXML()throws Exception{
		
		logger.info("getAllCountriesXML" );
		
		ModelAndView mav = new ModelAndView();
		CountryList list = service.getAllCountries();
		mav.addObject(list);
		mav.setView(xmlView);
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countries/{id}.json")
	public ModelAndView getCountryJSON( 
			@PathVariable("id") String country_id) throws Exception{
		
		logger.info("getCountryJSON");
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject(service.getCountry(country_id));
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countries/{id}.xml")
	public ModelAndView getCountryXML ( 
			@PathVariable("id") String country_id) throws Exception{
		
		logger.info("getCountryXML");
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView);
		mav.addObject(service.getCountry(country_id));
		
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/countries/{id}.xml")
	public ModelAndView updateCountryXML(
			@PathVariable("id") String country_id, 
			@RequestBody Country country) throws Exception {
		RestResult result = new RestResult();
		if (country_id.equals(country.getCountry_id())) {
			service.updateCountry(country);
			result.setResult(true);
		} else {
			result.setResult(false);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView);
		mav.addObject(result);
		
		return mav;
	}
	
	//POST 데이터는 다음과 같은 형식으로..
	//{"country_id":"AR","country_name":"Argentina","region_name":"Americas"}
	@RequestMapping(method=RequestMethod.POST, value="/countries/{id}.json")
	public ModelAndView updateCountryJSON(
			@PathVariable("id") String country_id, 
			@RequestBody Country country) throws Exception {
		
		RestResult result = new RestResult();

		if (country_id.equals(country.getCountry_id())) {
			service.updateCountry(country);
			result.setResult(true);
		} else {
			result.setResult(false);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject(result);
		
		return mav;
	}
}

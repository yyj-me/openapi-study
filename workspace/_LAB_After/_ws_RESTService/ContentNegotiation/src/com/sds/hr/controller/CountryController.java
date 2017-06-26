package com.sds.hr.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sds.hr.service.CountryService;
import com.sds.hr.vo.Country;
import com.sds.hr.vo.CountryList;
import com.sds.hr.vo.RestResult;

@Controller
public class CountryController {
	private static Logger logger = Logger.getLogger(CountryController.class);
	
	@Autowired
	private CountryService service;
	
	private String XML_VIEW_NAME = "xmlTemplate";
	
	@RequestMapping(method=RequestMethod.GET, value="/countries")
	public ModelAndView getCountriesXML()throws Exception{
		
		logger.info("getAllCountries" );
		
		ModelAndView mav = new ModelAndView();
		CountryList list = service.getAllCountries();
		mav.addObject("list", list);
		mav.setViewName(XML_VIEW_NAME);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countries/{id}")
	public ModelAndView getCountryJSON( 
			@PathVariable("id") String country_id) throws Exception{
		
		logger.info("getCountry");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("country", service.getCountry(country_id));
		mav.setViewName(XML_VIEW_NAME);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/countries/{id}")
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
		mav.addObject("result", result);
		mav.setViewName(XML_VIEW_NAME);
		return mav;
	}

}

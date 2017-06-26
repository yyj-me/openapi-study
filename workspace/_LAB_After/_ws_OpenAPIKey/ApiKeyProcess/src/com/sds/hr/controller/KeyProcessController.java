package com.sds.hr.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.openapi.ApiKeyProcessor;
import org.thinker.openapi.ApiKeyVO;

@Controller
public class KeyProcessController {
	private static Logger logger = Logger.getLogger(CountryController.class);
	
	@Resource(name="ApiKeyGenerator")
	private ApiKeyProcessor service;
	
	@RequestMapping(method=RequestMethod.GET, value="/key/request")
	public String getForm(){
		
		logger.info("Key Generation Form!!");
		
		return "keygen";
	}
	
	@RequestMapping(value="/key/makeKey", method=RequestMethod.POST)
	public ModelAndView makeApiKey(ApiKeyVO apiKeyVO)throws Exception{

		String keyValue = service.requestNewAPIKey(apiKeyVO);
		logger.info("hostName: " + apiKeyVO.getHostName());
		logger.info("api key: "+ keyValue);
		ModelAndView mav = new ModelAndView();
		mav.addObject("apikey", keyValue);
		mav.setViewName("keyresult");
		
		return mav;
	}
}

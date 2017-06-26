package com.sds.hr.controller;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thinker.openapi.ApiKeyProcessor;

import com.sds.hr.common.CommonUtil;
import com.sds.hr.service.CountryService;
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
	
	@Resource(name="ApiKeyGenerator")
	private ApiKeyProcessor keyProcessor;

	@RequestMapping(method=RequestMethod.GET, value="/countries.json")
	public ModelAndView getCountriesJSONWithCallback(
			@RequestParam(value="callback",required=false) String callback,
			@RequestParam(value="key") String key,
			HttpServletRequest request)throws Exception{
		
		logger.info("getAllCountrieJSONWithCallback" );
		
		String origin = (String)request.getHeader("Origin");
		String apikey = URLEncoder.encode(key,"UTF-8");
		ModelAndView mav = new ModelAndView();
		String json = "";
		
		logger.info("##origin : " + origin);
		logger.info("##api key : " + apikey);
		RestResult result = null;
		
		//유효하지 않으면 Exception 발생 JSON 응답 결과를 false로..
		try {
			keyProcessor.checkApiKey(origin, apikey);
			CountryList list = service.getAllCountries();
			result = new RestResult(true, "", list);
		} catch (Exception e) {
			result = new RestResult(false, e.getMessage(), null);
		}
		json = CommonUtil.convertObjectToJson(result);
		
		if (callback != null && !callback.equals("")) {
			json = callback + "(" + json + ")";
		}
		
		mav.addObject("jsonData", json);
		mav.setViewName("json");
		System.out.println(json);
		
		return mav;
	}
}

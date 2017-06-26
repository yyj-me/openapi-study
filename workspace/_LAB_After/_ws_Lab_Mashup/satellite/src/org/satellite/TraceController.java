package org.satellite;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;



@Controller
public class TraceController {
	
	private static Logger logger = Logger.getLogger(TraceController.class);
	
	@Resource(name="TraceService")
	private TraceService service;

	
	@Resource(name="jsonTemplate")
	private View jsonView;

	
	@RequestMapping("/main")
	public String getMain(){
		return "main";
	}
	
	
	@RequestMapping("/{tcode}")
	public ModelAndView getGeoPoint(@PathVariable("tcode") String tcode,
			@RequestParam(value="callback", required=false) String callback) throws RuntimeException, Exception{
		
		TerminatorGeo tGeo =  service.getGeoPoint(tcode);
		String json = CommonUtil.convertObjectToJson(tGeo);
		if (callback != null && !callback.equals("")) {
			json = callback + "(" + json + ")";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonData", json);
		mav.setViewName("position");
		System.out.println(json);
		return mav;
	}
	
	
	
	@RequestMapping("/all")
	public ModelAndView getAllGeoPoint(
			@RequestParam(value="callback", required=false) String callback) throws Exception{
		
		List<TerminatorGeo> list =  service.getAllGeoPoint();
		String json = CommonUtil.convertObjectToJson(list);
		if (callback != null && !callback.equals("")) {
			json = callback + "(" + json + ")";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsonData", json);
		mav.setViewName("position");
		System.out.println(json);
		
		return mav;
	}
}

package com.tfactory.web;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.tfactory.common.CommonUtil;
import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;
import com.tfactory.service.ModelService;

@Controller
public class ModelOpenController {
	private static Logger logger = Logger.getLogger(ModelOpenController.class);
	
	@Resource(name="jsonTemplate")
	private View jsonView;
	
	@Resource(name="xmlTemplate")
	private View xmlView;
	
	@Resource(name="ModelService")
	private ModelService service;
	
	@RequestMapping("/open/xml/{mcode}")
	public ModelAndView getXmlModel(@PathVariable("mcode") String mcode) throws ModelException {
		logger.info("getXMLModel");
		
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView);
		mav.addObject(service.viewModel(mcode));
		return mav;
	}
	
	@RequestMapping("/open/json/{mcode}")
	public ModelAndView getJsonModel(@PathVariable("mcode") String mcode) throws ModelException {
		logger.info("getJsonModel");
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject(service.viewModel(mcode));
		return mav;
	}
	
	@RequestMapping("/open/json/all")
	public ModelAndView getJsonModels() throws ModelException {
		logger.info("getJsonModelAll");
		
		List<ModelVO> list = service.viewAllModels();
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/open/jsonp/{mcode}")	
	public ModelAndView getJsonModel(@PathVariable("mcode") String mcode,
			@RequestParam(value="callback", required=false) String callback) throws Exception {
		ModelAndView mav = new ModelAndView();
		ModelVO vo = service.viewModel(mcode);
		String json = CommonUtil.convertObjectToJson(vo);
		if(callback != null && !callback.equals("")) {
			json = callback + "(" + json + ")";
		}
		mav.addObject("jsonData", json);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	@RequestMapping("/open/jsonp/all")	
	public ModelAndView getJsonModels(@RequestParam(value="callback", required=false) String callback) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		List<ModelVO> list = service.viewAllModels();
		String json = CommonUtil.convertObjectToJson(list);
		if(callback != null && !callback.equals("")) {
			json = callback + "(" + json + ")";
		}
		mav.addObject("jsonData", json);
		mav.setViewName("jsonView");
		return mav;
		
	}
}

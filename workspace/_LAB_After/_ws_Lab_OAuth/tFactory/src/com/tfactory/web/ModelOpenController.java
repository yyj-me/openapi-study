package com.tfactory.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
	public ModelAndView getXmlModel(@PathVariable("mcode") String mcode , HttpServletRequest request, HttpServletResponse response)throws ModelException{
		
		
		logger.info("getXMLModel");
//		response.setHeader("Cache-Control", "no-cache");  
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost/snj");
		String origin = request.getHeader("Origin");
		logger.info("request "  + origin);
		ModelAndView mav = new ModelAndView();
		mav.setView(xmlView);
		mav.addObject(service.viewModel(mcode));
		return mav; 
	}
	
	@RequestMapping("/open/json/{mcode}")

	public ModelAndView getJsonModel( @PathVariable("mcode") String mcode, HttpServletResponse response)throws ModelException{
		
		logger.info("getJsonModel");
		
//		response.setHeader("Cache-Control", "no-cache");  
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		mav.addObject(service.viewModel(mcode));
		
		return mav;
	}
	
	

	
	@RequestMapping("/open/json/all")
	public ModelAndView getJsonModels(HttpServletResponse response)throws ModelException{
		
		logger.info("getJsonModelS" );
		
//		response.setHeader("Cache-Control", "no-cache");  
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		
		ModelAndView mav = new ModelAndView();
		
		List<ModelVO> list = service.viewAllModels();
		
		mav.addObject("list",list);
		
		mav.setView(jsonView);
		
		return mav;
	}
	
}














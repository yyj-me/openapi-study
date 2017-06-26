package com.snj;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SNJController {

	private static Logger logger = Logger.getLogger(SNJController.class);
	
	@Resource(name="TFactoryConnectService")
	private TFactoryConnectService tFactoryConnectService;

	@RequestMapping("/main")
	public String getMainPage(){
		logger.info("main...............");
		return "main";
	}
	
	@RequestMapping("/viewModel")
	public String viewModel(){
		logger.info("view1 ");
		return "viewModel";
	}
	
	@RequestMapping("/viewModel2")
	public String viewModel2(){
		logger.info("view2 ");
		return "viewModel2";
	}
	
	@RequestMapping("/viewModel3")
	public String viewModel3(){
		logger.info("view3 ");
		return "viewModel3";
	}
	
	@RequestMapping("/connect")
	public ModelAndView connect(
			 @RequestParam(value="tcode", required=false) String code){
		logger.info("#####connect ");
		String result = "";
		if (code == null) 
			code = "all";
		
		try {
			result = tFactoryConnectService.connectTFactory(code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		ModelAndView mav = new ModelAndView();
		mav.addObject("json", result);
		mav.setViewName("getJsonTModel");
		return mav;
	}
	
	
}

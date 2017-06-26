package com.snj;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SNJController {

	private static Logger logger = Logger.getLogger(SNJController.class);

	@RequestMapping("/main")
	public String getMainPage(){
		logger.info("main...............");
		return "main";
	}
	
	@RequestMapping("/viewModel")
	public String viewModel(){
		logger.info("view ");
		return "viewModel";
	}
	
	@RequestMapping("/viewPosition")
	public String findModel(String mcode){
		
		logger.info("find");
		
		return "viewPosition";
		
	}
	
	
}

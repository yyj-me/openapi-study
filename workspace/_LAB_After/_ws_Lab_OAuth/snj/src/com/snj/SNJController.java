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
	
	//with oauth
	@RequestMapping("/viewModel2")
	public String viewModel2(){
		logger.info("view2 ");
		return "viewModel2";
	}
	
}

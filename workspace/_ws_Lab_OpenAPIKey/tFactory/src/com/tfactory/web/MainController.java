package com.tfactory.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfactory.service.ModelService;



@Controller
public class MainController {
	
	@Resource(name="ModelService")
	private ModelService service;

	
	@RequestMapping("/main")
	public String startMain(){
		return "main";
	}
	

	
}

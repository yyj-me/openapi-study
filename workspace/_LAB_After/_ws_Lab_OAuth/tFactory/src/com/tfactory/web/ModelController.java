package com.tfactory.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tfactory.common.ExceptionUtil;
import com.tfactory.common.FileUtil;
import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;
import com.tfactory.service.ModelService;

@Controller
public class ModelController {

	private static Logger logger = Logger.getLogger(ModelController.class);

	@Resource(name="ModelService")
	private ModelService service;
	
	
	@RequestMapping(value="/form", method=RequestMethod.GET )
	public String getForm(){
		
		logger.info("form input");
		
		return "input";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String regist(ModelVO vo, @RequestParam("uploadFile") MultipartFile uploadFile){
		
		String result = "";
		
		logger.info("ModelVO : " + vo);
		
		try {
			String uploadedName = FileUtil.saveFile(uploadFile);
			vo.setMimg(uploadedName);
			service.registNewModel(vo);
			
			result = "inputSuccess";
		} catch (ModelException e) {
			logger.warn(e.getMessage());
			logger.warn(ExceptionUtil.getMessage(e));
			result = "fail";
		} catch(IOException e){
			e.printStackTrace();
			FileUtil.deleteFile(vo.getMimg());
		}
		
	
		return result;
	}
	
	
	@RequestMapping(value="/viewAll", method=RequestMethod.GET)
	public ModelAndView viewAll()throws ModelException{
		
		List<ModelVO> modelList =service.viewAllModels();

		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", modelList);
		
		mav.setViewName("list");
		
		
		return mav;
		
		
	}
	
	@RequestMapping(value="/view/{model}")
	public ModelAndView viewModel(@PathVariable("model") String mcode)throws ModelException{
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("model", service.viewModel(mcode));
		mav.setViewName("view");
		
		return mav;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

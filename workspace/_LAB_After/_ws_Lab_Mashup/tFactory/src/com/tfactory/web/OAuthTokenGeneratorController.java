package com.tfactory.web;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.provider.generator.OAuthTokenGenerator;
import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenStorage;
import com.tfactory.oauth.OAuthTokenVO;


@Controller

public class OAuthTokenGeneratorController {
	
	
	@Resource(name="oauthStorage")
	private OAuthTokenStorage storage;	
	
	@RequestMapping("/genOAuth")
	public ModelAndView generate(HttpServletRequest request)throws OAuthException{
		
		String newConsumerKey =  UUID.randomUUID() + (System.currentTimeMillis()+"");
		
		OAuthTokenVO vo = new OAuthTokenVO(newConsumerKey);
		
		OAuthTokenGenerator generator = new OAuthTokenGenerator();
		generator.generateTokens(vo);
		
		storage.addNewTokens(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("oauthToken", vo);
		mav.setViewName("showOAuthToken");
		
		return mav;
		
	}

}

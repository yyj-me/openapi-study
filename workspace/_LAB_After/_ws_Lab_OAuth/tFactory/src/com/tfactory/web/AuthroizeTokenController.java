package com.tfactory.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenStorage;
import com.tfactory.oauth.OAuthTokenVO;

@Controller
@RequestMapping(value="/authorize") 
public class AuthroizeTokenController {
	
	
	@Resource(name="oauthStorage")
	private OAuthTokenStorage oauthStorage;
	


	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView startLogin(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("authorize");
		
		return mav;
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void login(@RequestParam("oauth_token") String oauth_token , HttpServletRequest request, HttpServletResponse response)throws OAuthException{
		
		
		
		System.out.println("AuthorizeServlet  POST:" + oauth_token);
		
		

		
		String terminatorId = request.getParameter("userid");
		
		System.out.println("login... user info.. " + terminatorId);
		
		request.getSession().setAttribute("user", terminatorId);
		
		
		
		System.out.println("AuthorizeServlet :" + oauth_token);
		
		OAuthTokenVO tokenVO = oauthStorage.getTokenWithOAuthToken(oauth_token);
		
		String oauth_token_verifier = tokenVO.getVerifier();
		
		StringBuilder builder = new StringBuilder();
		
		
		builder.append("oauth_token=" + oauth_token +"&");
		builder.append("oauth_verifier=" + oauth_token_verifier);
		
		//등록된 callback 또는 처음 요청시 확인한 callback 으로 응답하도록 해야 함.
		//일단은 jcornor.com 으로..
		String callbackURL = "http://jcornor.com:8000/snj/callback.do?"+builder.toString();
		
		response.setStatus(302);
		response.setHeader("Location", callbackURL);
		
	}
	
}

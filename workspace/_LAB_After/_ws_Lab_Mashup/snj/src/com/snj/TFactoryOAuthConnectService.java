package com.snj;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.RequestTokenVO;
import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

@Controller("TFactoryOAuthConnectService")
public class TFactoryOAuthConnectService {
	
	private static Logger logger = Logger.getLogger(TFactoryConnectService.class);
	
	@RequestMapping(value="/connectTFactory", method=RequestMethod.GET)
	public ModelAndView connectTFactory(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = null;
		
		
		if((String)request.getSession().getAttribute("oauthToken") == null){
			TokenSender sender = new TokenSender(TokenSender.TYPE_HEADER);
			
			RequestTokenVO vo = new RequestTokenVO();
			vo.setMethod("POST");
			
			vo.setRequestURL("http://tFactory.com:8000/tFactory/model/request_token");
			vo.setCallbackURL("http://jcornor.com:8000/snj/callback");
			
			vo.setConsumerKey("86e616bf-28e8-4736-8669-53b93b22370e1310337819274");
			vo.setConsumerSecretKey("8f4625284127b1e3ec39687d81cf7a9e");

			vo.sign();
	
			System.out.println(vo.getSignature());
		
			
			sender.sendHttpClient(vo);
			
			System.out.println("CONSUMER request Oauth token: " +vo.getRequestOauthToken());
			System.out.println("CONSUMER request Oauth token Secret: " +vo.getRequestOauthTokenSecret());
			
			
			CookieUtil.addCookie(response,"requestToken", vo.getRequestOauthToken());
			CookieUtil.addCookie(response,"requestTokenSecret",vo.getRequestOauthTokenSecret());
			
			request.getSession().setAttribute("consumerkey", vo.getConsumerKey());
			request.getSession().setAttribute("consumerSecretkey", vo.getConsumerSecretKey());
			
			System.out.println("### Request Token : " + vo.getRequestOauthToken());
			response.setStatus(302);
			response.setHeader("Location", "http://tFactory.com:8000/tFactory/model/authorize?oauth_token=" +vo.getRequestOauthToken());
		
		}else {
			
			
			System.out.println((String)request.getSession().getAttribute("oauthToken") );
			
			if(request.getSession().getAttribute("oauthTokenSecret") != null){
				resourceTFactory(request, response);
			}
			
			
		}
		
		
		
		
		
		return mav;
	}
	
	@RequestMapping(value="/connectTFactory", method=RequestMethod.POST)
	public @ResponseBody String connectTFactoryWithPost(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		//ModelAndView mav = new ModelAndView();
		
		
		System.out.println("\n\nTFactoryOAuthConnect");
		
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL("http://tFactory.com:8000/tFactory/model/request_token");
		
		
		vo.setConsumerKey("86e616bf-28e8-4736-8669-53b93b22370e1310337819274");
		vo.setConsumerSecretKey("8f4625284127b1e3ec39687d81cf7a9e");
		

		
		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));

		System.out.println("\n\n------------------------\n"+vo.getConsumerSecretKey());
		System.out.println((String)request.getSession().getAttribute("oauthTokenSecret"));
		
		
		vo.sign();
		System.out.println(vo.getBaseString());
		
		
		
		System.out.println(vo.getSignature());
		
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_HEADER);

		finalSender.sendHttpClient(vo);
				
		System.out.println(vo.getResult());
		
		response.setContentType("application/json");
		response.getWriter().print(vo.getResult());
		
		//mav.addObject(vo.getResult());
		
		return vo.getResult();
	}
	
	@RequestMapping(value="/resourceTFactory", method=RequestMethod.GET)
	public void resourceTFactory(HttpServletRequest request, HttpServletResponse response )throws Exception{


		
		System.out.println("\nresourceTFactory");
		System.out.println(request.getParameter("mcode"));
		
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		//vo.setRequestURL("http://twitter.com/statuses/friends_timeline.json");
		vo.setRequestURL("http://tFactory.com:8000/tFactory/model/resource");
		
		
		vo.setConsumerKey("86e616bf-28e8-4736-8669-53b93b22370e1310337819274");
		vo.setConsumerSecretKey("8f4625284127b1e3ec39687d81cf7a9e");
		

		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));
		
		if(request.getParameter("mcode") != null){
			vo.setParam("mcode="+request.getParameter("mcode"));
		}

		System.out.println("\n\n------------------------\n"+vo.getConsumerSecretKey());
		System.out.println((String)request.getSession().getAttribute("oauthTokenSecret"));
		
		
		vo.sign();
		System.out.println(vo.getBaseString());
		
		
		
		System.out.println(vo.getSignature());
		
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_HEADER);

		finalSender.sendHttpClient(vo);

		
		System.out.println(vo.getResult());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().print(vo.getResult());
	}
	
}

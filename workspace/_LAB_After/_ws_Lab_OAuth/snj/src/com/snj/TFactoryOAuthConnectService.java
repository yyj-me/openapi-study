package com.snj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.AccessTokenVO;
import org.thinker.oauth.RequestTokenVO;
import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

@Controller("TFactoryOAuthConnectService")
public class TFactoryOAuthConnectService {

	private static Logger logger = Logger.getLogger(TFactoryOAuthConnectService.class);
	
	//tFactory site id
	//will by changed!!!!
	
	@RequestMapping(value="/connectTFactory", method=RequestMethod.GET)
	public ModelAndView connectTFactory(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = null;
		if((String)request.getSession().getAttribute("oauthToken") == null){
			
			TokenSender sender = new TokenSender(TokenSender.TYPE_HEADER);
			
			RequestTokenVO vo = new RequestTokenVO();
			vo.setMethod("POST");
			
			vo.setRequestURL(Configuration.OAUTH_REQUESTTOKEN_URL);
			vo.setCallbackURL(Configuration.OAUTH_CALLBACK_URL);
			
			vo.setConsumerKey(Configuration.CONSUMERKEY);
			vo.setConsumerSecretKey(Configuration.CONSUMERSECRET);
			vo.sign();
	
			System.out.println(vo.getSignature());
			
			sender.sendHttpClient(vo);
			
			System.out.println("CONSUMER request Oauth token: " +vo.getRequestOauthToken());
			System.out.println("CONSUMER request Oauth token Secret: " +vo.getRequestOauthTokenSecret());
			
			CookieUtil.addCookie(response,"requestToken", vo.getRequestOauthToken());
			CookieUtil.addCookie(response,"requestTokenSecret",vo.getRequestOauthTokenSecret());

			request.getSession().setAttribute("consumerkey", vo.getConsumerKey());
			request.getSession().setAttribute("consumerSecretkey", vo.getConsumerSecretKey());
			
			
			response.setStatus(302);
			response.setHeader("Location", Configuration.OAUTH_AUTHORIZE_URL+"?oauth_token=" +vo.getRequestOauthToken());
		
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
		vo.setRequestURL(Configuration.OAUTH_REQUESTTOKEN_URL);
		
		vo.setConsumerKey(Configuration.CONSUMERKEY);
		vo.setConsumerSecretKey(Configuration.CONSUMERSECRET);
		
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
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(vo.getResult());
		
		//mav.addObject(vo.getResult());
		
		return vo.getResult();
	}

	@RequestMapping(value="/callback", method=RequestMethod.GET)
	public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String twitterMsg = request.getQueryString();
		
		System.out.println("tFactory access message: " + twitterMsg);
		
		AccessTokenVO accessVO = new AccessTokenVO(twitterMsg);
		
		//accessVO.setRequestURL("https://api.twitter.com/oauth/access_token");
		
		accessVO.setRequestURL(Configuration.OAUTH_ACCESSTOKEN_URL);

		accessVO.setConsumerKey(Configuration.CONSUMERKEY);
		accessVO.setConsumerSecretKey(Configuration.CONSUMERSECRET);
		accessVO.setVerifier(accessVO.getVerifier());
		accessVO.setRequestOauthTokenSecret(CookieUtil.findValueByName(request.getCookies(), "requestTokenSecret"));
		accessVO.setMethod("POST");
		
		accessVO.sign();
		
		TokenSender sender = new TokenSender(TokenSender.TYPE_HEADER);
		
		System.out.println("========================CALLBACK======================");
		System.out.println("getRequestOauthToken: "+accessVO.getRequestOauthToken());
		System.out.println("getVerifier"+accessVO.getVerifier());
		
		sender.sendHttpClient(accessVO);
		
		System.out.println("after access: "+accessVO.getRequestOauthToken());
		System.out.println("after access: "+accessVO.getRequestOauthTokenSecret());
		
//			CookieUtil.addCookie(response, "oauthToken", accessVO.getRequestOauthToken());
//			CookieUtil.addCookie(response, "oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
		
		request.getSession().setAttribute( "oauthToken", accessVO.getRequestOauthToken());
		request.getSession().setAttribute("oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
			
		response.setStatus(302);
		response.setHeader("Location", Configuration.LOCAL_ACCESS_RESOURCE_URL);
		
		
	}

	
	@RequestMapping(value="/resourceTFactory", method=RequestMethod.GET)
	public void resourceTFactory(HttpServletRequest request, HttpServletResponse response )throws Exception{


		
		System.out.println("\nresourceTFactory");
		System.out.println(request.getParameter("mcode"));
		
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");

		vo.setRequestURL(Configuration.RESOURCE_URL);
		vo.setConsumerKey(Configuration.CONSUMERKEY);
		vo.setConsumerSecretKey(Configuration.CONSUMERSECRET);

		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));
		
		if(request.getParameter("mcode") != null){
			vo.setParam("mcode="+request.getParameter("mcode"));
		}
		
		System.out.println("\n\n------------------------\n"+vo.getConsumerSecretKey());
		System.out.println("####mcode : " + vo.getParam());
		System.out.println((String)request.getSession().getAttribute("oauthTokenSecret"));
		
		vo.sign();
		System.out.println(vo.getBaseString());
		
		System.out.println(vo.getSignature());
		
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_HEADER);

		finalSender.sendHttpClient(vo);
		System.out.println(vo.getResult());

		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(vo.getResult());

	}
}

package com.snj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thinker.oauth.AccessTokenVO;
import org.thinker.oauth.TokenSender;

@Controller
@RequestMapping("/callback")
public class CallbackController {

	@RequestMapping(method=RequestMethod.GET)
	public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String twitterMsg = request.getQueryString();
		
		System.out.println("tFactory access message: " + twitterMsg);
		
		
		
		AccessTokenVO accessVO = new AccessTokenVO(twitterMsg);
		
		
		
		//accessVO.setRequestURL("https://api.twitter.com/oauth/access_token");
		
		accessVO.setRequestURL("http://tFactory.com:8000/tFactory/model/access_token");


		accessVO.setConsumerKey("86e616bf-28e8-4736-8669-53b93b22370e1310337819274");
		accessVO.setConsumerSecretKey("8f4625284127b1e3ec39687d81cf7a9e");
		

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
//			
		
		request.getSession().setAttribute( "oauthToken", accessVO.getRequestOauthToken());
		request.getSession().setAttribute("oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
			
		

		response.setStatus(302);
		response.setHeader("Location", "viewModel2.do");
		
		
	}
}

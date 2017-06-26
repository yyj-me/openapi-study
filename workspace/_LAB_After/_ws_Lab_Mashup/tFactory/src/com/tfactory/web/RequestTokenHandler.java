package com.tfactory.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thinker.oauth.provider.parser.HeaderParser;
import org.thinker.oauth.provider.parser.OAuthParser;
import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenStorage;
import com.tfactory.oauth.OAuthTokenVO;


@Controller
public class RequestTokenHandler {
	private static final long serialVersionUID = 1L;
	
	
	@Resource(name="oauthStorage")
	private OAuthTokenStorage oauthStorage;
	
	
	


	@RequestMapping("/request_token")
	public void process(HttpServletRequest request, HttpServletResponse response) throws OAuthException, IOException {

		
		System.out.println("\n\nRequestTokenServlet");
		
		String requestConsumerKey = HeaderParser.findHeaderString(request.getHeader("Authorization"), "oauth_consumer_key");
		
		
		System.out.println("requestConsumerKey : " + requestConsumerKey);
		
		if(requestConsumerKey == null){
			throw new OAuthException("Cannot find your ConsumerKey");
		}
		
		OAuthTokenVO tokenVO = oauthStorage.getToken(requestConsumerKey);
		
		String consumerSecretKey = tokenVO.getConsumerSecretKey();
		
		String method = request.getMethod();
		String requestURL = "http://"+request.getServerName() +":"+ request.getServerPort()+ request.getRequestURI();
		String headerValue = request.getHeader("Authorization");
		
		
		System.out.println("method : " + method);
		System.out.println("requestURL :" + requestURL);
		System.out.println("headerValue:" + headerValue);
		
		OAuthParser parser  = new HeaderParser(method, requestURL, headerValue, consumerSecretKey);
		
		
		String oauth_token = tokenVO.getRequestToken();
		
		String oauth_token_secret = tokenVO.getRequestTokenSecret();
		
		String oauth_callback_confirmed = "true";
		
		StringBuilder builder = new StringBuilder();
		
		
		builder.append("oauth_token=" + oauth_token +"&");
		builder.append("oauth_token_secret=" + oauth_token_secret +"&");
		builder.append("oauth_callback_confirmed=" + oauth_callback_confirmed);
		

		
		response.setContentType("application/x-www-form-urlencoded");
		response.getWriter().println(builder.toString());
		
	
		
	}

}

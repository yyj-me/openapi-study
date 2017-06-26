package com.tfactory.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thinker.oauth.provider.parser.HeaderParser;
import org.thinker.oauth.provider.parser.OAuthParser;
import org.thinker.oauth.provider.util.OAuthException;

import com.tfactory.oauth.OAuthTokenStorage;
import com.tfactory.oauth.OAuthTokenVO;

@Controller
public class AccessTokenHandler {

	private static final long serialVersionUID = 1L;
	
	
	@Resource(name="oauthStorage")
	private OAuthTokenStorage oauthStorage;
	

	@RequestMapping("/access_token")
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OAuthException {
		
		System.out.println("\n\nAccessTokenServlet.................................");
		System.out.println("===================================================");
		
		
		String requestConsumerKey = HeaderParser.findHeaderString(request.getHeader("Authorization"), "oauth_consumer_key");
		//String requestConsumerKey = request.getParameter("oauth_consumer_key");	
		
		OAuthTokenVO tokenVO =  oauthStorage.getToken(requestConsumerKey);

		String consumerSecretKey = tokenVO.getConsumerSecretKey();
		String oauthTokenSecretKey = tokenVO.getRequestTokenSecret();
		
		String method = request.getMethod();
		String requestURL = "http://"+request.getServerName() +":"+ request.getServerPort()+ request.getRequestURI();
		String headerValue = request.getHeader("Authorization");
		
		System.out.println("method : " + method);
		System.out.println("requestURL :" + requestURL);
		System.out.println("headerValue:" + headerValue);
		
		
		OAuthParser parser  = new HeaderParser(method, requestURL, headerValue, consumerSecretKey,oauthTokenSecretKey);

		
		String oauth_token = tokenVO.getAccessToken();
		
		String oauth_token_secret = tokenVO.getAccessTokenSecret().trim();
		
		
		
		StringBuilder builder = new StringBuilder();
		
		
		builder.append("oauth_token=" + oauth_token +"&");
		builder.append("oauth_token_secret=" + oauth_token_secret+"&" );
		builder.append("other=" + oauth_token_secret );
		
		
		response.setContentType("application/x-www-form-urlencoded");
		response.getWriter().println(builder.toString());
		
	}

}

package org.thinker.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thinker.oauth.AccessTokenVO;
import org.thinker.oauth.OAuthUtil;
import org.thinker.oauth.RequestTokenVO;
import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class TwitterCallBackServlet
 */
public class TwitterCallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterCallBackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String twitterMsg = request.getQueryString();
		
		System.out.println("@@@@ twitter access message: " + twitterMsg);
		//twitter access message: oauth_token=UjiftZ4y0jK5b5Rhg8uSkIPgPXoLUgiNcz20ZHNfL8&oauth_verifier=DtRuf1jjbhxqqEfIFDNORNgKyGuSSlIG7VOnSA9o

		
		
		AccessTokenVO accessVO = new AccessTokenVO(twitterMsg);
		
		accessVO.setRequestURL("https://api.twitter.com/oauth/access_token");
		//accessVO.setRequestURL("http://localhost:8000/tSimpleProvider/auth/access_token");

		accessVO.setConsumerKey("qwXidQXejg9XKKxKbbIpw");
		accessVO.setConsumerSecretKey("4rkSyaT89ip5DIJDz9neWBKdU3UXo4FXaaCnNDfhmw");

//		accessVO.setConsumerKey("4a0cd612-0b4b-4828-bada-02488d93ad19");
//		accessVO.setConsumerSecretKey("9d83e467186e5ee50a701a203125ae88");
		

		accessVO.setVerifier(accessVO.getVerifier());
		accessVO.setRequestOauthTokenSecret(CookieUtil.findValueByName(request.getCookies(), "requestTokenSecret"));
		accessVO.setMethod("GET");
		
		accessVO.sign();
		
		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		
		try {
		
			System.out.println("========================CALLBACK======================");
			System.out.println("@@@ getRequestOauthToken : "+accessVO.getRequestOauthToken());
			System.out.println("@@@ getRequestOauthTokenSecret: " + accessVO.getRequestOauthTokenSecret());
			System.out.println("@@@ getVerifier : "+accessVO.getVerifier());
			
			sender.sendHttpClient(accessVO);
			
			System.out.println("@@@ after access: "+accessVO.getRequestOauthToken());
			System.out.println("@@@ after access secret: "+accessVO.getRequestOauthTokenSecret());
			
//			CookieUtil.addCookie(response, "oauthToken", accessVO.getRequestOauthToken());
//			CookieUtil.addCookie(response, "oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
//			
			request.getSession().setAttribute("oauthToken", accessVO.getRequestOauthToken());
			request.getSession().setAttribute("oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//response.sendRedirect("hello");
		response.sendRedirect("home_timeline.jsp");
	}


}

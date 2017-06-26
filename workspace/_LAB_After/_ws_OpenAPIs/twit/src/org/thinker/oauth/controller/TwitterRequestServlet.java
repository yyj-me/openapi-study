package org.thinker.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thinker.oauth.OAuthMsgConstants;
import org.thinker.oauth.OAuthUtil;
import org.thinker.oauth.RequestTokenVO;
import org.thinker.oauth.TokenSender;



/**
 * Servlet implementation class TwitterRequestServlet
 */
public class TwitterRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//consumerKey=2aa7eeb1-d517-4e9a-88da-28b4822da36c1306945419754, consumerSecretKey=09bf04a3e0ee3dc22ac49263e110ef8f, requestToken=2aa7eeb1-d517-4e9a-88da-28b4822da36c13069454197541306945419768995000, requestTokenSecret=5479d974eaa14604ada5149feff84c1c, accessToken=09bf04a3e0ee3dc22ac49263e110ef8f13069454197691160002aa7eeb1-d517-4e9a-88da-28b4822da36c13069454197541306945419768995000, accessTokenSecret=481fb19369769b29bf0cfde59ca5d5de
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		
		RequestTokenVO vo = new RequestTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL("https://api.twitter.com/oauth/request_token");
		vo.setCallbackURL("http://localhost:8000/twit/callback");		
		vo.setConsumerKey("qwXidQXejg9XKKxKbbIpw");
		vo.setConsumerSecretKey("4rkSyaT89ip5DIJDz9neWBKdU3UXo4FXaaCnNDfhmw");
		
//		vo.setRequestURL("http://localhost:8000/tSimpleProvider/auth/request_token");
//		vo.setCallbackURL("http://localhost:8000/twit/callback");
//		vo.setConsumerKey("4a0cd612-0b4b-4828-bada-02488d93ad19");
//		vo.setConsumerSecretKey("9d83e467186e5ee50a701a203125ae88");
		vo.sign();

		//pnQTTeTU2FmB%2BVp%2Fez5IP945Hh0%3D
		System.out.println("@@@@ Signature : " + vo.getSignature());
		
		try {
			sender.sendHttpClient(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("@@@ CONSUMER request Oauth token: " +vo.getRequestOauthToken());
		System.out.println("@@@ CONSUMER request Oauth token Secret: " +vo.getRequestOauthTokenSecret());
		
		
		CookieUtil.addCookie(response,"requestToken", vo.getRequestOauthToken());
		CookieUtil.addCookie(response,"requestTokenSecret",vo.getRequestOauthTokenSecret());
		
		
		response.sendRedirect("http://api.twitter.com/oauth/authorize?oauth_token=" +vo.getRequestOauthToken());
//		response.setStatus(302);
//		response.setHeader("Location", "http://api.twitter.com/oauth/authorize?oauth_token=" +vo.getRequestOauthToken());
//		response.setHeader("Location", "http://localhost:8000/tSimpleProvider/auth/authorize?oauth_token=" +vo.getRequestOauthToken());
	}

}

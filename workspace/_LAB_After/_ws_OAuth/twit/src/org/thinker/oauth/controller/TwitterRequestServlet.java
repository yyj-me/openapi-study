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
		//이곳에 코드를 작성하시오.
		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		
		RequestTokenVO vo = new RequestTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL(OAuthSetting.REQUEST_TOKEN_URL);
		vo.setCallbackURL(OAuthSetting.CALLBACK_URL);		
		vo.setConsumerKey(OAuthSetting.CONSUMER_KEY);
		vo.setConsumerSecretKey(OAuthSetting.CONSUMER_SECRET);
		vo.sign();

		try {
			sender.sendHttpClient(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CookieUtil.addCookie(response,"requestToken", vo.getRequestOauthToken());
		CookieUtil.addCookie(response,"requestTokenSecret",vo.getRequestOauthTokenSecret());
		
		
		response.sendRedirect(OAuthSetting.AUTHORIZE_URL +
				"?oauth_token=" +vo.getRequestOauthToken());
	}

}

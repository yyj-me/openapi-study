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
		//이곳에 코드를 작성하시오.
		String twitterMsg = request.getQueryString();

		AccessTokenVO accessVO = new AccessTokenVO(twitterMsg);
		accessVO.setRequestURL(OAuthSetting.ACCESS_TOKEN_URL);
		accessVO.setConsumerKey(OAuthSetting.CONSUMER_KEY);
		accessVO.setConsumerSecretKey(OAuthSetting.CONSUMER_SECRET);
		accessVO.setRequestOauthTokenSecret(CookieUtil.findValueByName(request.getCookies(), "requestTokenSecret"));
		accessVO.setMethod("GET");
		accessVO.sign();
		
		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		try {
			sender.sendHttpClient(accessVO);
			request.getSession().setAttribute("oauthToken", accessVO.getRequestOauthToken());
			request.getSession().setAttribute("oauthTokenSecret", accessVO.getRequestOauthTokenSecret());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("hello");
	}


}

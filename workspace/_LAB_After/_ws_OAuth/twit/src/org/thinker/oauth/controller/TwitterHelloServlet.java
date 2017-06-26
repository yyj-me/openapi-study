package org.thinker.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class TwitterHelloServlet
 */
public class TwitterHelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterHelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이곳에 코드를 작성하시오.
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("oauthToken"));
		System.out.println(session.getAttribute("oauthTokenSecret"));
		
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		//vo.setRequestURL("https://api.twitter.com/1.1/statuses/home_timeline.json");
		vo.setRequestURL("http://tfactory.com:8000/tSimpleProvider/auth/myinfo");
		vo.setConsumerKey(OAuthSetting.CONSUMER_KEY);
		vo.setConsumerSecretKey(OAuthSetting.CONSUMER_SECRET);
		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));

		vo.sign();
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_PARAM);
	
		try {
			finalSender.sendHttpClient(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.setContentType("application/json");
		response.setContentType("application/xml");
		response.getWriter().print(vo.getResult());		

	}
	
}

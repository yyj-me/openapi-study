package org.thinker.oauth.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.thinker.oauth.OAuthUtil;
import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class UpdateStatus
 */
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("POST");
		//request.setCharacterEncoding("UTF-8");
		String status = request.getParameter("status");

		System.out.println("####" + status);
		String url = "http://api.twitter.com/1/statuses/update.json?status=" + OAuthUtil.encode(status);
//		String url = "http://api.twitter.com/1/statuses/update.xml?status=" + status;
		System.out.println("#####" + url);
		
		vo.setRequestURL(url);
		vo.setConsumerKey("qwXidQXejg9XKKxKbbIpw");
		vo.setConsumerSecretKey("4rkSyaT89ip5DIJDz9neWBKdU3UXo4FXaaCnNDfhmw");
		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));
		vo.sign();
		
		System.out.println("@@@@ Consumer Key : " + vo.getConsumerKey());
		System.out.println("@@@@ Consumer Secret : " + vo.getConsumerSecretKey());
		System.out.println("@@@@ Token : " + vo.getRequestOauthToken());
		System.out.println("@@@@ Token Secret: " + vo.getRequestOauthTokenSecret());
		
		System.out.println("@@@@ BaseString : " + vo.getBaseString());
		System.out.println("@@@@ Signature : " + vo.getSignature());
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_PARAM);
	
		try {
			finalSender.sendHttpClient(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(vo.getRequestURL());
		
		System.out.println(vo.getResult());
		
		response.setContentType("text/plain");
		response.getWriter().print(vo.getResult());		
	}

}

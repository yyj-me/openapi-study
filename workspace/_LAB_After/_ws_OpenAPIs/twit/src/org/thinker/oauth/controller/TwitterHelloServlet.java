package org.thinker.oauth.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		
		String page = request.getParameter("page");
		String count = request.getParameter("count");
		vo.setRequestURL("http://twitter.com/statuses/home_timeline.json");
		//vo.setRequestURL("http://twitter.com/statuses/home_timeline.json?page=" + page +"&count=" + count);
		//vo.setRequestURL("http://localhost:8000/tSimpleProvider/auth/myinfo");
		System.out.println("@@@@" + vo.getRequestURL());
		
		vo.setConsumerKey("qwXidQXejg9XKKxKbbIpw");
		vo.setConsumerSecretKey("4rkSyaT89ip5DIJDz9neWBKdU3UXo4FXaaCnNDfhmw");
		
//		vo.setConsumerKey("4a0cd612-0b4b-4828-bada-02488d93ad19");
//		vo.setConsumerSecretKey("9d83e467186e5ee50a701a203125ae88");
	
		vo.setRequestOauthToken((String)request.getSession().getAttribute("oauthToken"));
		vo.setRequestOauthTokenSecret((String)request.getSession().getAttribute("oauthTokenSecret"));

		vo.sign();
		System.out.println("@@@@ Consumer Key : " + vo.getConsumerKey());
		System.out.println("@@@@ Consumer Secret : " + vo.getConsumerSecretKey());
		System.out.println("@@@@ Token : " + vo.getRequestOauthToken());
		System.out.println("@@@@ Token Secret: " + vo.getRequestOauthTokenSecret());
		
		System.out.println("@@@@ BaseString : " + vo.getBaseString());
		System.out.println("@@@@ Signature : " + vo.getSignature());
		TokenSender finalSender = new TokenSender(TokenSender.TYPE_HEADER);
	
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

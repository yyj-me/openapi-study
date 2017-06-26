package org.thinker.oauth.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class GetHomeTimeLine2
 */
public class GetHomeTimeLine2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHomeTimeLine2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		
		String max_id = request.getParameter("max_id");
		String count = request.getParameter("count");
		String url = "http://twitter.com/statuses/home_timeline.json";
		url += "?count=" + count;
		if (max_id != null && !max_id.equals(""))
		url += "&max_id=" + max_id;
			
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

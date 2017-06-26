package com.sds.testprovider.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.parameter.RequestTokenParam;

import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.RequestTokenService;
import com.sds.testprovider.util.TokenGenerator;

@Controller
public class RequestTokenController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private RequestTokenService requestTokenService;
	
	@RequestMapping(value="request_token")
	public ModelAndView requestToken(HttpServletRequest request) throws Exception {
		
		//1. QueryString �Ǵ� Post �Ķ���� �Ľ�
		RequestTokenParam param = new RequestTokenParam(request);
		System.out.println("### requestTokenParam : " + param.getConsumerKey());
		//2. tbl_oauth_key ���̺����� ConsumerSecret ���� �о��.
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		String consumerSecret = consumerVO.getConsumerSecret();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("request_token");
		System.out.println("### ConsumerVO : " + consumerVO);
		try {
			//3. signature validation!! ��ȿ���� ������ ���� �߻�!
			param.validateRequestToken(consumerSecret);
			//4. ��ȿ�ϴٸ� RequestToken �����Ͽ� tbl_request_token�� ����!!
			//�ǹ������� ServletContext �� ���� ����(����������)�� ����...
			RequestTokenVO tokenVO = new RequestTokenVO();
			tokenVO.setConsumerKey(consumerVO.getConsumerKey());
			//parameter�� �Ѿ�� callback�� �ش� ConsumerKey�� ��ϵ� 
			//callbackurl�� ��ġ�ϴ��� Ȯ���� �Ŀ� oauth_callback_confirmed ���� 
			//true�� �ο��ϵ��� ����������.
			//authorize ó���� �ݵ�� ��ϵ� callback���θ� redirect�ϵ��� 
			//������ �ʿ伺�� ����.
			tokenVO.setCallback(param.getCallback());
			TokenGenerator.generateRequestToken(tokenVO);
			requestTokenService.createRequestToken(tokenVO);
			
			System.out.println("### tokenVO2 : " + tokenVO);
			
			String oauth_callback_confirmed = "true";
			
			StringBuilder builder = new StringBuilder();
			builder.append("oauth_token=" + tokenVO.getRequestToken() +"&");
			builder.append("oauth_token_secret=" + tokenVO.getRequestTokenSecret() +"&");
			builder.append("oauth_callback_confirmed=" + oauth_callback_confirmed);
			
			mav.addObject("isValid", true);
			mav.addObject("message", builder.toString());
		} catch (Exception e) {
			mav.addObject("isValid", false);
			mav.addObject("message", e.getMessage());
		}
		 
		return mav;
	}
}
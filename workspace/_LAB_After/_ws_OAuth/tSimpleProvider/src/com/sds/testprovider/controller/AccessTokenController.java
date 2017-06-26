package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.parameter.AccessTokenParam;
import org.thinker.oauth.util.OAuthMsgConstants;
import org.thinker.oauth.util.OAuthUtil;

import com.sds.testprovider.model.AccessTokenVO;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.RequestTokenService;
import com.sds.testprovider.service.UsersService;
import com.sds.testprovider.util.TokenGenerator;

@Controller
public class AccessTokenController {
	
	@Autowired
	private RequestTokenService requestTokenService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ConsumerService consumerService;
	
	@RequestMapping(value="access_token")
	public ModelAndView getAccessToken(HttpServletRequest request) throws Exception {
		//1. 파라미터 파싱
		AccessTokenParam param = new AccessTokenParam(request);
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		System.out.println("###1 : " + param.getRequestToken());
		RequestTokenVO requestTokenVO = requestTokenService.getRequestToken(param.getRequestToken());
		System.out.println("###2 : " + requestTokenVO.getUserNo() );
		UsersVO usersVO = usersService.selectUserByUserNo(requestTokenVO.getUserNo());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("access_token");
		
		try {
			//2. signature validation!! 유효하지 않으면 예외 발생!
			param.validateRequestToken(requestTokenVO.getRequestTokenSecret(),requestTokenVO.getVerifier() , consumerVO.getConsumerSecret());
			//2.1 유효하다면 RequestToken 테이블의 레코드 삭제 : 임시 TOken이므로..
			requestTokenService.deleteRequestToken(requestTokenVO.getRequestToken());
			
			//3. AccessToken 생성
			AccessTokenVO accessTokenVO = TokenGenerator.generateAccessToken(usersVO, consumerVO);
			
			StringBuilder builder = new StringBuilder();
			builder.append(OAuthMsgConstants.OAUTH_TOKEN + "=" + accessTokenVO.getAccessToken() +"&");
			builder.append(OAuthMsgConstants.OAUTH_TOKEN_SECRET + "=" + accessTokenVO.getAccessTokenSecret() +"&");
			builder.append("userno=" + accessTokenVO.getUserNo() + "&");
			builder.append("userid=" + accessTokenVO.getUserID());
			
			mav.addObject("isValid", true);
			mav.addObject("message", builder.toString());

		} catch (Exception e) {
			mav.addObject("isValid", false);
			mav.addObject("message", e.getMessage());
		}
		
		return mav;
	}
}

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
		//아래 return 문을 주석 처리하고 코드를 작성합니다.
		return null;
	}
}

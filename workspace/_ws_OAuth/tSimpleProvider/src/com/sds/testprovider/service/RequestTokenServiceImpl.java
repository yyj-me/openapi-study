package com.sds.testprovider.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sds.testprovider.dao.RequestTokenDAO;
import com.sds.testprovider.model.RequestTokenVO;

@Service("RequestTokenService")
public class RequestTokenServiceImpl implements RequestTokenService {

	private static Logger logger = Logger.getLogger(UsersServiceImpl.class); 
	
	@Resource(name="RequestTokenDAO")
	private RequestTokenDAO requestTokenDAO;
	
	@Override
	public void createRequestToken(RequestTokenVO requestTokenVO)
			throws Exception {
		requestTokenDAO.createRequestToken(requestTokenVO);
	}

	@Override
	public void deleteRequestToken(String requestToken) throws Exception {
		requestTokenDAO.deleteRequestToken(requestToken);

	}

	@Override
	public RequestTokenVO getRequestToken(String requestToken) throws Exception {
		return requestTokenDAO.selectRequestToken(requestToken);
	}

	@Override
	public void updateUserNo(RequestTokenVO requestTokenVO) throws Exception {
		requestTokenDAO.updateUserNo(requestTokenVO);
	}

}

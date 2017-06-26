package com.tfactory.oauth;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.thinker.oauth.provider.util.OAuthException;

import com.ibatis.sqlmap.client.SqlMapClient;


@Component("oauthStorage")
public class OAuthTokenStorageImpl implements OAuthTokenStorage {
	
	private static Logger logger = Logger.getLogger(OAuthTokenStorageImpl.class); 

	@Resource(name="sqlMapClient")
	private SqlMapClient sqlMapClient;
	
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@Override
	public void addNewTokens(OAuthTokenVO vo) throws OAuthException {
		
		logger.info(vo);
		
		try {
			sqlMapClient.insert("oauth.create", vo);
		} catch (SQLException e) {
			throw new OAuthException(e.getMessage());
		}
	}

	@Override
	public OAuthTokenVO getToken(String consumerKey) throws OAuthException {
		logger.info(consumerKey);
		
		OAuthTokenVO result = null;
		
		try {

			result = (OAuthTokenVO)sqlMapClient.queryForObject("oauth.select",consumerKey);
			
		} catch (SQLException e) {
			throw new OAuthException(e.getMessage());
		}
		return result;
	}

	@Override
	public OAuthTokenVO getTokenWithOAuthToken(String oauthToken)
			throws OAuthException {
		OAuthTokenVO result = null;
		
		try {

			result = (OAuthTokenVO)sqlMapClient.queryForObject("oauth.select2",oauthToken);
			
		} catch (SQLException e) {
			throw new OAuthException(e.getMessage());
		}
		return result;
	}

}

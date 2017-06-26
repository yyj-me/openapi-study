package com.sds.testprovider.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.testprovider.model.OAuthKeyVO;
import com.sds.testprovider.model.UsersVO;

@Repository("OAuthKeyDAO")
public class OAuthKeyDAOImpl extends SqlMapClientDaoSupport implements OAuthKeyDAO {

	private static Logger logger = Logger.getLogger(OAuthKeyDAOImpl.class); 
	
    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }
	
	@Override
	public void createOAuthToken(OAuthKeyVO oAuthKeyVO) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### Create OAuth Key --- " + oAuthKeyVO);
		try {
			getSqlMapClient().insert("oAuthKey.create", oAuthKeyVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteOAuthToken(String consumerKey) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### DELETE OAuthKey : " + consumerKey);
		try {
			getSqlMapClient().delete("oAuthKey.delete", consumerKey);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public OAuthKeyVO selectByConsumerKey(String consumerKey) throws Exception  {
		logger.info("### Select User --- " + consumerKey);
		
		OAuthKeyVO result = null;
		try {
			result = (OAuthKeyVO)getSqlMapClient().queryForObject("oAuthKey.selectByConsumerKey", consumerKey);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	@Override
	public List<OAuthKeyVO> selectByUserId(String userId) throws Exception  {
		logger.info("### Select User --- " + userId);
		
		List<OAuthKeyVO> list = null;
		try {
			list = (List<OAuthKeyVO>)getSqlMapClient().queryForList("oAuthKey.selectByUserID", userId);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

}

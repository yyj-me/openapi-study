package com.sds.testprovider.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.UsersVO;

@Repository("ConsumerDAO")
public class ConsumerDAOImpl extends SqlMapClientDaoSupport implements ConsumerDAO {

	private static Logger logger = Logger.getLogger(ConsumerDAOImpl.class); 
	
    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }
	
	@Override
	public void createOAuthToken(ConsumerVO oAuthKeyVO) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### Create Consumer --- " + oAuthKeyVO);
		try {
			getSqlMapClient().insert("Consumer.create", oAuthKeyVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteOAuthToken(String consumerKey) throws Exception  {
		// TODO Auto-generated method stub
		logger.info("### DELETE Consumer : " + consumerKey);
		try {
			getSqlMapClient().delete("Consumer.delete", consumerKey);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public ConsumerVO selectByConsumerKey(String consumerKey) throws Exception  {
		logger.info("### Select By ConsumerKey --- " + consumerKey);
		
		ConsumerVO result = null;
		try {
			result = (ConsumerVO)getSqlMapClient().queryForObject("Consumer.selectByConsumerKey", consumerKey);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ConsumerVO> selectByUserId(String userId) throws Exception  {
		logger.info("### Select By UserID --- " + userId);
		
		List<ConsumerVO> list = null;
		try {
			list = (List<ConsumerVO>)getSqlMapClient().queryForList("Consumer.selectByUserID", userId);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

}

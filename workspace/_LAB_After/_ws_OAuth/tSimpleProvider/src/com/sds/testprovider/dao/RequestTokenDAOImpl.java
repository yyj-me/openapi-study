package com.sds.testprovider.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.testprovider.model.RequestTokenVO;

@Repository("RequestTokenDAO")
public class RequestTokenDAOImpl extends SqlMapClientDaoSupport implements RequestTokenDAO {

	private static Logger loggr = Logger.getLogger(RequestTokenDAOImpl.class); 
	
    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }
	
	@Override
	public void createRequestToken(RequestTokenVO requestTokenVO)  throws Exception{
		// TODO Auto-generated method stub
		logger.info("### Create RequestToken ---- " + requestTokenVO);
		try {
			getSqlMapClient().insert("requestToken.create", requestTokenVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void deleteRequestToken(String requestToken) throws Exception {
		// TODO Auto-generated method stub
		logger.info("### Delete RequestToken ---- " + requestToken);
		try {
			getSqlMapClient().delete("requestToken.delete", requestToken);
			//10분 이상 경과된 requestToken은 삭제
			getSqlMapClient().delete("requestToken.deleteExpired");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public RequestTokenVO selectRequestToken(String requestToken) throws Exception {
		RequestTokenVO result = null;
		try {
			result = (RequestTokenVO)getSqlMapClient().queryForObject("requestToken.select", requestToken);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	@Override
	public void updateUserNo(RequestTokenVO requestTokenVO) throws Exception {
		try {
			getSqlMapClient().update("requestToken.updateUserNo", requestTokenVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

}

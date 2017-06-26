package com.sds.testprovider.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.testprovider.model.UsersVO;

@Repository("UsersDAO")
public class UsersDAOImpl extends SqlMapClientDaoSupport  implements UsersDAO {

	private static Logger logger = Logger.getLogger(UsersDAOImpl.class); 
	
	@Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }

	@Override
	public void createUser(UsersVO usersVO) throws Exception {
		// TODO Auto-generated method stub
		logger.info("### Create User ---- " + usersVO);
		try {
			getSqlMapClient().insert("users.createUser", usersVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public UsersVO selectUser(UsersVO usersVO) throws Exception {
		logger.info("### Select User --- " + usersVO);
		
		UsersVO result = null;
		try {
			result = (UsersVO)getSqlMapClient().queryForObject("users.selectUserByUserID", usersVO);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	@Override
	public int nextUserNo() throws Exception {
		int nextNo= 0;
		try {
			nextNo = (Integer)getSqlMapClient().queryForObject("users.getNextNo");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return nextNo;
	}

	@Override
	public UsersVO selectUserByUserNo(long userno) throws Exception {
		logger.info("### Select User By UserNo --- " + userno);
		
		UsersVO result = null;
		try {
			result = (UsersVO)getSqlMapClient().queryForObject("users.selectUserByUserNo", userno);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}
}

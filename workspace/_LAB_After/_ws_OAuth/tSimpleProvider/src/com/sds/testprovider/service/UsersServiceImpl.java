package com.sds.testprovider.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sds.testprovider.dao.UsersDAO;
import com.sds.testprovider.dao.UsersDAOImpl;
import com.sds.testprovider.model.UsersVO;

@Service("UsersService")
public class UsersServiceImpl implements UsersService {

	private static Logger logger = Logger.getLogger(UsersServiceImpl.class); 
	
	@Resource(name="UsersDAO")
	private UsersDAO usersDAO;
	
	@Override
	public void createUsers(UsersVO usersVO) throws Exception {
		usersVO.setUserno(usersDAO.nextUserNo());
		usersDAO.createUser(usersVO);
	}

	@Override
	public UsersVO selectUsers(UsersVO usersVO) throws Exception {
		
		return usersDAO.selectUser(usersVO);
	}

	@Override
	public UsersVO selectUserByUserNo(long userno) throws Exception {
		return usersDAO.selectUserByUserNo(userno);
	}

}

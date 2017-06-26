package org.thinker.openapi;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import org.thinker.openapi.ApiKeyException;
import org.thinker.openapi.ApiKeyRepository;
import org.thinker.openapi.ApiKeyVO;

import com.ibatis.sqlmap.client.SqlMapClient;



@Repository("ApiKeyRepository")
public class ApiKeyRepositoryImpl extends SqlMapClientDaoSupport implements ApiKeyRepository {

	private static Logger loggr = Logger.getLogger(ApiKeyRepositoryImpl.class); 

    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }

    
    /* (non-Javadoc)
	 * @see com.tfactory.openkey.ApiKeyRepository#create(com.tfactory.openkey.ApiKeyVO)
	 */
    @Override
	public void create(ApiKeyVO vo)throws ApiKeyException{
		try {
			getSqlMapClient().insert("apiKey.insert", vo );
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }


	/* (non-Javadoc)
	 * @see com.tfactory.openkey.ApiKeyRepository#read(java.lang.String)
	 */
	@Override
	public ApiKeyVO read(String pk) throws ApiKeyException{

		ApiKeyVO vo = null;
		
		try{
			vo = (ApiKeyVO)getSqlMapClient().queryForObject("apiKey.select",pk);
		}catch(SQLException e){
			throw new ApiKeyException(e);
		}
		
		return vo;
	}


	@Override
	public void update(String pk) throws ApiKeyException {

		try{
			getSqlMapClient().update("apiKey.updateCnt",pk);
		}catch(SQLException e){
			throw new ApiKeyException(e);
		}
		
	}
    
	

	
}

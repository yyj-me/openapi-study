package com.tfactory.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;


@Repository("ModelDAO")
public class ModelDAOImpl extends SqlMapClientDaoSupport implements ModelDAO{

	private static Logger loggr = Logger.getLogger(ModelDAOImpl.class); 

    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }

	/* (non-Javadoc)
	 * @see com.tfactory.model.ModelDAO#create(com.tfactory.model.ModelVO)
	 */
	@Override
	public void create(ModelVO vo)throws ModelException{
		logger.info(vo);
		
		try {
			//test ibatis configration
			String result = (String)getSqlMapClient().queryForObject("test.getTime");
			logger.info("current time test : " + result);
			
			getSqlMapClient().insert("model.create", vo);

			
		} catch (SQLException e) {
			throw new ModelException(e.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.tfactory.model.ModelDAO#read(java.lang.String)
	 */
	@Override
	public ModelVO read(String mCode)throws ModelException{
		logger.info(mCode);
		
		ModelVO result = null;
		
		try {

			result = (ModelVO)getSqlMapClient().queryForObject("model.select",mCode);
			
		} catch (SQLException e) {
			throw new ModelException(e.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.tfactory.model.ModelDAO#update(com.tfactory.model.ModelVO)
	 */
	@Override
	public void update(ModelVO vo)throws ModelException{
		
		logger.info(vo);
		
		try {

			getSqlMapClient().update("model.update", vo);

			
		} catch (SQLException e) {
			throw new ModelException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tfactory.model.ModelDAO#delete(java.lang.String)
	 */
	@Override
	public void delete(String mCode)throws ModelException{
		
		logger.info(mCode);
		
		try {

			getSqlMapClient().delete("model.delete",mCode);

		} catch (SQLException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public List<ModelVO> readAll() throws ModelException {
		
		logger.info("readAll");
		
		List<ModelVO> list = null;
		
		try {

			list = (List<ModelVO>)getSqlMapClient().queryForList("model.selectAll");
			logger.info("current time test : " + list );
			
		} catch (SQLException e) {
			throw new ModelException(e.getMessage());
		}
		
		return list;
	}
	
}

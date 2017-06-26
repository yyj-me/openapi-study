package com.sds.hr.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.hr.vo.Country;


@Repository("CountryDAO")
public class CountryDAOImpl extends SqlMapClientDaoSupport  implements CountryDAO {

	private static Logger loggr = Logger.getLogger(CountryDAOImpl.class); 

    @Autowired
    public final void setSqlMapClientWorkaround(SqlMapClient smc) {
        super.setSqlMapClient(smc);
    }
    
	@Override
	public List<Country> getAllCountries() throws Exception {
		logger.info("DAO-getCountriesAll");
		
		List<Country> list = (List<Country>)getSqlMapClient().queryForList("Country.selectAll");
		return list;
	}

	@Override
	public Country getCountry(String country_id) throws Exception {
		logger.info("DAO-getCountry : " + country_id);
		Country c = (Country)getSqlMapClient().queryForObject("Country.select",country_id);
		return c;
	}

	@Override
	public void updateCountry(Country country) throws Exception {
		logger.info("DAO-updateCountry");
		getSqlMapClient().update("Country.updateCountry", country);
	}

}

package com.sds.hr.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sds.hr.util.SqlMapClientUtil;
import com.sds.hr.vo.Country;

public class CountryServiceImpl implements CountryService {
	
	private SqlMapClient sqlMap;

	public CountryServiceImpl() {
		this.sqlMap = SqlMapClientUtil.getSqlMapInstance();		
	}

	
	@Override
	public List<Country> getAllCountries() throws Exception {
		List<Country> list = (List<Country>)sqlMap.queryForList("selectAll");
		return list;
	}

	@Override
	public Country getCountry(String country_id) throws Exception {
		Country country = (Country)sqlMap.queryForObject("select", country_id);
		return country;
	}

	@Override
	public void updateCountry(Country country)  throws Exception{
		sqlMap.update("updateCountry", country);
	}

}

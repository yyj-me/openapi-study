package com.sds.hr.test;

import org.codehaus.jackson.map.ObjectMapper;

import com.sds.hr.vo.Country;

public class TestClient {
	public static void main(String[] args) throws Exception {
		Country c = new Country("KO", "Korea", "Asia");
		
		ObjectMapper mapper = new ObjectMapper();
		String strJson = mapper.writeValueAsString(c);
		System.out.println(strJson);
		
		strJson = strJson.replaceAll("Korea", "England");
		Country c2 = mapper.readValue(strJson, Country.class);
		
		
	}
}



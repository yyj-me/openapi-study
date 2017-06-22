package com.snj.test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snj.TFactoryConnectService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "file:WebContent/WEB-INF/dispatcher-servlet.xml")
public class RestTemplateTest {

	@Resource(name="TFactoryConnectService")
	TFactoryConnectService service;
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void testView() throws Exception{
		
		String str = service.connectTFactory("T1");
		
		System.out.println(str);
		
	}
}

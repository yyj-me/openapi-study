package com.tfactory.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tfactory.model.ModelDAO;
import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "file:WebContent/WEB-INF/applicationContext.xml")
public class TFactoryDAOTest {
	
	private static final Logger logger = Logger.getLogger("Test");
	
	@Resource(name="ModelDAO")
	ModelDAO dao;
	
	
	
	@Before
	public void init(){
		
		
	}
	
	@Test
	public void testCreate() throws ModelException{
		
		ModelVO vo = new ModelVO();
		vo.setMcode("TX");
		vo.setMname("TX Model");
		vo.setMdesc("TX Model...");
		vo.setMimg("TX.jpg");
		dao.create(vo);

	}
	
	
	@Test
	public void testReadOne()throws ModelException{
		
		ModelVO model = dao.read("T1000");
		
		logger.info(model);
		
	}
	
	@Test
	public void testDelete()throws ModelException{
		
		dao.delete("X00");
	}
	
	
	@Test
	public void testUpdate()throws ModelException{
		ModelVO vo = new ModelVO();
		vo.setMcode("TX");
		vo.setMname("TX Model update");
		vo.setMdesc("TX Model update.....");
		vo.setMimg("TX.jpg");
		
		dao.update(vo);
	}
	
	@Test
	public void testAll()throws ModelException{
		
		List<ModelVO> list = dao.readAll();
		
		logger.info(list);
		
	}

}

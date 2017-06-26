package com.tfactory.model;

import java.util.List;

public interface ModelDAO {

	public  void create(ModelVO vo) throws ModelException;

	public  ModelVO read(String mCode) throws ModelException;

	public  void update(ModelVO vo) throws ModelException;

	public  void delete(String mCode) throws ModelException;
	
	public List<ModelVO> readAll()throws ModelException;

}
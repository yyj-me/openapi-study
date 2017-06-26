package com.tfactory.service;

import java.util.List;

import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;

public interface ModelService {

	public void registNewModel(ModelVO model)throws ModelException;
	
	public void modifyModel(ModelVO model)throws ModelException;
	
	public void removeModel(String modelCode)throws ModelException;
	
	public ModelVO viewModel(String modelCode)throws ModelException;
	
	public List<ModelVO> viewAllModels() throws ModelException;
	
	
}

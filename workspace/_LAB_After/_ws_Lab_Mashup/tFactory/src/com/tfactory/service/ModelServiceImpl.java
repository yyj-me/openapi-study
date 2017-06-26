package com.tfactory.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tfactory.model.ModelDAO;
import com.tfactory.model.ModelException;
import com.tfactory.model.ModelVO;

@Service("ModelService")
public class ModelServiceImpl implements ModelService{

	@Resource(name="ModelDAO")
	private ModelDAO dao;

	@Override
	public void registNewModel(ModelVO model) throws ModelException {
		
		dao.create(model);
	}

	@Override
	public void modifyModel(ModelVO model) throws ModelException {
		
		dao.update(model);
		
	}

	@Override
	public void removeModel(String modelCode) throws ModelException {
		
		dao.delete(modelCode);
		
	}

	@Override
	public ModelVO viewModel(String modelCode) throws ModelException {
		
		return dao.read(modelCode);
	}

	@Override
	public List<ModelVO> viewAllModels() throws ModelException {
		
		return dao.readAll();
	}
	
	
}

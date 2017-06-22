package com.multi.test.biz.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.multi.test.biz.vo.EmployeeVO;

@Service
public class EmployeeDAO {
	private static List<EmployeeVO> list;
	
	static {
		list = new ArrayList<EmployeeVO>();
		list.add(new EmployeeVO(1,"홍길동","gdhong@gmail.com"));
		list.add(new EmployeeVO(2,"이몽룡","mrlee@yahoo.com"));
		list.add(new EmployeeVO(3,"성춘향","chsung@gmail.com"));
	}
	
	public List<EmployeeVO> getEmployees() {
		return EmployeeDAO.list;
	}
	
	public EmployeeVO getEmployeeById(int id) {
		if (id > 0 && id <= list.size()) {
			return list.get(id-1);
		} else {
			return null;
		}
	}
}

package com.multi.test.view.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multi.test.biz.dao.EmployeeDAO;
import com.multi.test.biz.vo.EmployeeListVO;
import com.multi.test.biz.vo.EmployeeVO;


@RestController
@Api(value="EmployeesRestController", description="직원 정보 서비스")
@RequestMapping(value = "/employees")
public class EmployeeRestController
{
	@Autowired
	private EmployeeDAO employeeDAO;
	
    @RequestMapping(method=RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    @ApiOperation(value = "직원 정보 조회")
    public @ResponseBody EmployeeListVO getAllEmployees()
    {
    	List<EmployeeVO> emps = employeeDAO.getEmployees();
    	EmployeeListVO listVO = new EmployeeListVO(emps.size(), emps);
    	return listVO;
        
    }
     
    @RequestMapping(value = "/{id}", method=RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    @ApiOperation(value = "사번으로 직원 정보 조회")
    public @ResponseBody ResponseEntity<EmployeeVO> getEmployeeById (
    		@ApiParam(name = "id", required = true, value = "조회할 직원의 id값")@PathVariable("id") int id)
    {
    	EmployeeVO vo = employeeDAO.getEmployeeById(id);
    	if (vo == null) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<EmployeeVO>(vo, HttpStatus.OK);

    }
}

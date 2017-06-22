package com.multi.test.biz.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="employees")
public class EmployeeListVO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer totalcount; 
	private List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
	
    public EmployeeListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeListVO(Integer totalcount, List<EmployeeVO> employees) {
		super();
		this.totalcount = totalcount;
		this.employees = employees;
	}

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
 
    public List<EmployeeVO> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<EmployeeVO> employees) {
        this.employees = employees;
    }
}


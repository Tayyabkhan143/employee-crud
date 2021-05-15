package com.tayyab.employeecrud.DAO;

import java.util.List;

import com.tayyab.employeecrud.entity.EmployeeEntity;

public interface EmployeeDao {
	
	public List<EmployeeEntity> getAllEmployeeEntitys() ;
	 
    public EmployeeEntity getEmployeeEntity(Integer id) ;
 
    public Integer addEmployeeEntity(EmployeeEntity EmployeeEntity);
 
    public void updateEmployeeEntity(EmployeeEntity EmployeeEntity) ;
 
    public void deleteEmployeeEntity(Integer id) ;
    
}


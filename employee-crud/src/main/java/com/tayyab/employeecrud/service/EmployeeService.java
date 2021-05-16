package com.tayyab.employeecrud.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tayyab.employeecrud.DAO.EmployeeDao;
import com.tayyab.employeecrud.controller.EmpHibernateController;
import com.tayyab.employeecrud.entity.EmployeeEntity;
import com.tayyab.employeecrud.model.Employee;
import com.tayyab.employeecrud.util.Util;
 
@Service
public class EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
 
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    Util util;
 
    
    public List<Employee> getAllEmployees() {
    	
    	List<Employee> empList = new ArrayList<>();
    	
    	try {
			List<EmployeeEntity> empEntityList = employeeDao.getAllEmployeeEntitys();
			
			if(!empEntityList.isEmpty()) {
				for(EmployeeEntity empEntity: empEntityList) {
					Employee emp = util.convertEntityToModel(empEntity);
					empList.add(emp);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getAllEmployees : "+e.getMessage());
		}
        return empList;
    }
    
    
    public Employee getEmployee(Integer id) {
    	
    	Employee employee = null;
    	try {
			EmployeeEntity empEntity = employeeDao.getEmployeeEntity(id);
			
			if(null !=empEntity) {
				employee = util.convertEntityToModel(empEntity);
			}
		} catch (Exception e) {
			logger.error("Exception in getEmployee : "+e.getMessage());
			
		}
        return employee;
    }
 
    
    public Integer addEmployee(EmployeeEntity employeeEntity) {
    	
    	Integer id = null ;
    	try {
			id =  employeeDao.addEmployeeEntity(employeeEntity);
		} catch (Exception e) {
			logger.error("Exception in addEmployee : "+e.getMessage());
			id = -1;
			
		}
		return id;
    }
 
    
    public void updateEmployee(EmployeeEntity employee) {
        try {
			employeeDao.updateEmployeeEntity(employee);
		} catch (Exception e) {
			logger.error("Exception in updateEmployee : "+e.getMessage());
			throw e;
		}
 
    }
 
    
    public void deleteEmployee(Integer id) {
        try {
			employeeDao.deleteEmployeeEntity(id);
		} catch (Exception e) {
			logger.error("Exception in deleteEmployee : "+e.getMessage());
			throw e;
		}
    }


}

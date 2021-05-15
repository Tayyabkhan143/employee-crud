package com.tayyab.employeecrud.util;

import org.springframework.stereotype.Component;

import com.tayyab.employeecrud.entity.EmployeeEntity;
import com.tayyab.employeecrud.model.Employee;

@Component
public class Util {
	
	public Employee convertEntityToModel(EmployeeEntity empEnt) {
		
		Employee emp = new Employee();
		emp.setId(empEnt.getEmpNo());
		emp.setBirthDate(empEnt.getBirthDate());
		emp.setFirstName(empEnt.getFirstName());
		emp.setLastName(empEnt.getLastName());
		emp.setGender(empEnt.getGender());
		emp.setHireDate(empEnt.getHireDate());
		
		return emp;
		

	}

}

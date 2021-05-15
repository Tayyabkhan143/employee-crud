package com.tayyab.employeecrud.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tayyab.employeecrud.entity.EmployeeEntity;
import com.tayyab.employeecrud.model.Employee;
import com.tayyab.employeecrud.repository.EmployeeRepository;
import com.tayyab.employeecrud.util.Util;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	Util util;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(value="/getByID",method = RequestMethod.GET)
	private ResponseEntity<EmployeeEntity> getEmployeeById(@RequestParam Integer id) {
		
		// http://localhost:8080/tayyab-springboot/employee/getByID?id=10001
		
		logger.info("Entered into getEmployeeById method with Id : "+id);
		
		ResponseEntity respEntity = null;
		Optional<EmployeeEntity> empEntity =  empRepo.findById(id);
		if (empEntity.isPresent()) {
			Employee emp = util.convertEntityToModel(empEntity.get());
			respEntity = new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
		else respEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return respEntity;
	}
	
	@RequestMapping(value="/getAll",method = RequestMethod.GET)
	private List<EmployeeEntity> getEmployees() {
		
		// http://localhost:8080/tayyab-springboot/employee/getAll
		
		logger.info("Entered into getEmployees method");
		return empRepo.findAll();
	}
	
	@RequestMapping(value="/getByFirstName/{firstName}",method = RequestMethod.GET)
	private List<EmployeeEntity> getEmployeeByFirstName(@PathVariable String firstName) {
		
		// http://localhost:8080/tayyab-springboot/employee/getByFirstName/Saniya
		
		logger.info("Entered into getEmployeeByFirstName method with First Name : "+firstName);
		
		return empRepo.findByFirstName(firstName);
	}
	
	@RequestMapping(value="/create",method = RequestMethod.POST)
	private EmployeeEntity createEmployee(@RequestBody EmployeeEntity emp) {
		
		// http://localhost:8080/tayyab-springboot/employee/create
		/* select max(emp_no) from employees; and then set empNo
		{
		    "empNo": 500000, 
		    "birthDate": "1997-07-19",
		    "firstName": "Tayyab",
		    "lastName": "Pathan",
		    "gender": "M",
		    "hireDate": "2018-11-26"
		}
		
		*/
		
		logger.info("Entered into createEmployee method with requestBody : "+emp.toString());
		
		return empRepo.save(emp);
	}
	
	@RequestMapping(value="/deleteByID",method = RequestMethod.POST)
	private String  deleteEmployeeByID(@RequestParam Integer empNo) {
		
		// http://localhost:8080/tayyab-springboot/employee/deleteByID?empNO=500000
		logger.info("Entered into deleteEmployeeByID method with EmpNo : "+empNo);
		
		try {
			empRepo.deleteById(empNo);
			return "employee "+empNo+" deleted from database";
			
		}
		catch(Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
		
		
	}
	
	
	
	
	
	


}

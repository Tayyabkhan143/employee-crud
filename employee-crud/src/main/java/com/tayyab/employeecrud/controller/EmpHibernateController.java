package com.tayyab.employeecrud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tayyab.employeecrud.DAO.EmployeeDaoImpl;
import com.tayyab.employeecrud.entity.EmployeeEntity;
import com.tayyab.employeecrud.model.Employee;
import com.tayyab.employeecrud.service.EmployeeService;

@RestController
@RequestMapping("/employee-hibernate")
public class EmpHibernateController {
	
	@Autowired
    EmployeeService employeeService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmpHibernateController.class);
	
	
	// 	http://localhost:8080/tayyab-springboot/employee-hibernate/getAllEmployees
	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
		logger.info("Entered into getAllEmployees method");
		ResponseEntity<List<Employee>> respEntity = null;
        List<Employee> employeeList = employeeService.getAllEmployees();
        if(!employeeList.isEmpty()) {
        	respEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
        }
        else {
        	respEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.NO_CONTENT);
        }
        
        return respEntity;
        
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public String goToHomePage() {
		logger.info("Entered into goToHomePage method");
        return "redirect:/getAllEmployees";
    }
	
	//	http://localhost:8080/tayyab-springboot/employee-hibernate/getEmployeeById/49999
	@RequestMapping(value = "/getEmployeeById/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		logger.info("Entered into getEmployeeById method with Id : "+id);
		ResponseEntity<Employee> respEntity = null;
		Employee emp = null;
		emp = employeeService.getEmployee(id);
		
		if(null != emp ) {
			respEntity = new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }
        else {
        	respEntity = new ResponseEntity<Employee>(emp, HttpStatus.BAD_REQUEST);

        }
        return respEntity;
    }
 
	//	http://localhost:8080/tayyab-springboot/employee-hibernate/createEmployee
	/* 
	  {
		    "empNo": 500000, 
		    "birthDate": "1997-07-19",
		    "firstName": "Tayyab",
		    "lastName": "Pathan",
		    "gender": "M",
		    "hireDate": "2018-11-26"
		}
	 */
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> createEmployee(@RequestBody EmployeeEntity empEntity) { 
		logger.info("Entered into updateEmployee method with request body : "+ empEntity.toString());
		ResponseEntity<Integer> respEntity = null;
		Integer id = -1;
        id =  employeeService.addEmployee(empEntity);
        if(id>0 ) {
        	respEntity = new ResponseEntity<Integer>(id, HttpStatus.OK);
        }
        else {
        	respEntity = new ResponseEntity<Integer>(id, HttpStatus.BAD_REQUEST);
        }
        
 
        return respEntity;
    }
 
//	http://localhost:8080/tayyab-springboot/employee-hibernate/updateEmployee
	/* 
	  {
		    "empNo": 500000, 
		    "birthDate": "1997-07-19",
		    "firstName": "Tayyab khan",
		    "lastName": "Pathan",
		    "gender": "M",
		    "hireDate": "2018-11-26"
		}
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeEntity empEntity) {
		logger.info("Entered into updateEmployee method with request body : "+empEntity.toString());
		ResponseEntity<String> respEntity = null;
        try {
			employeeService.updateEmployee(empEntity);
			respEntity = new ResponseEntity<String>("Employee updated successfully", HttpStatus.OK);

		} catch (Exception e) {
			respEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
        return respEntity;
    }
	
	//http://localhost:8080/tayyab-springboot/employee-hibernate/deleteEmployee/500000
	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		logger.info("Entered into deleteEmployee method with Id : "+id);
		ResponseEntity<String> respEntity = null;
        try {
			employeeService.deleteEmployee(id);
			respEntity = new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);

		} catch (Exception e) {
			respEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
        return respEntity;
 
    }   
}
 

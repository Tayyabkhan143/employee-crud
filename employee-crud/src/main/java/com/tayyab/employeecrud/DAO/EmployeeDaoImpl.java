package com.tayyab.employeecrud.DAO;

import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tayyab.employeecrud.entity.EmployeeEntity;
import com.tayyab.employeecrud.util.Util;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<EmployeeEntity> getAllEmployeeEntitys() {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		List<EmployeeEntity>  employeeList =null;
		try {
			employeeList = session.createQuery("from EmployeeEntity").list();
			t.commit();
		} catch (Exception e) {
			t.rollback();
			logger.error(e.getMessage());
			throw e;
		}
		finally {
			session.close();
		}
      
		return employeeList;
	}

	@Override
	public EmployeeEntity getEmployeeEntity(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		EmployeeEntity empEntity = null;
        try {
			empEntity  = session.get(EmployeeEntity.class, id);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			logger.error(e.getMessage());
			throw e;
		}
		finally {
			session.close();
		}
      
		return empEntity;
	}

	@Override
	public Integer addEmployeeEntity(EmployeeEntity employeeEntity) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		Integer id = null;
        try {
			 id = (Integer) session.save(employeeEntity);
			 t.commit();
		} catch (Exception e) {
			t.rollback();
			logger.error(e.getMessage());
			throw e;
		}
		finally {
			session.close();
		}
      
		return id;
	}

	@Override
	public void updateEmployeeEntity(EmployeeEntity employeeEntity) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
        try {
			 session.update(employeeEntity);
			 t.commit();
		} catch (Exception e) {
			t.rollback();
			logger.error(e.getMessage());
			throw e;
		}
		finally {
			session.close();
		}
		
	}

	@Override
	public void deleteEmployeeEntity(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			EmployeeEntity empEntity = (EmployeeEntity) session.load(EmployeeEntity.class, id);
	        if (null != empEntity) {
	            session.delete(empEntity);
	        }
	        t.commit();
		} catch (Exception e) {
			t.rollback();
			logger.error(e.getMessage());
			throw e;
		}
		finally {
			session.close();
		}
		
	}
 
	

}

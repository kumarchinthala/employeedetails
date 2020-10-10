package com.employee.employeedetails.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employeedetails.dao.EmployeeDAOImpl;
import com.employee.employeedetails.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAOImpl employeeDAOImpl;
	
	public Employee getEmployeeDeatils(String dbName , Integer id) {
		 
		return employeeDAOImpl.getEmployeeById(dbName,id);
		
	}

	public Optional<Employee> getDBToken(String dbName, String mailId, String password) {
		 
		return employeeDAOImpl.getTokenbyDBName(dbName,mailId,password);
	}

}

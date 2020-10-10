package com.employee.employeedetails.dao;

import java.util.Optional;

import com.employee.employeedetails.model.Employee;

public interface EmployeeDAO {
	public Employee getEmployeeById(String dbName, Integer id);

	public Optional<Employee> getTokenbyDBName(String dbName, String mailId, String password);

}

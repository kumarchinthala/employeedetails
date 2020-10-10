package com.employee.employeedetails.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
 		Employee emp = new Employee().builder().id(rs.getInt("id"))
 				  .name(rs.getString("name"))
 				 .dob(rs.getDate("dob"))
		         .mailId(rs.getString("mail_id"))
		         .password(rs.getString("password"))
		         .build();
		 
		return emp;
	}

}

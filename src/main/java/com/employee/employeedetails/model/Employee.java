package com.employee.employeedetails.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	private Integer id;
	private String name;
	private Date dob;
	private String mailId;
	private String password;

}

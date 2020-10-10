package com.employee.employeedetails.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.employeedetails.model.Employee;
import com.employee.employeedetails.model.EmployeeMapper;

@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcTemplate2;

	private final String SQL_FIND_ID = "select id,name,dob,mail_id ,password from employee where id = ?";
	private final String SQL_FIND_MAIL_PASSWORD_DB1 = "select * from employee where mail_id = ? and password = ?";
	private final String SQL_FIND_MAIL_PASSWORD_DB2 = "select * from employee where mail_id = ? and password = ?";;

	@Override
	public Employee getEmployeeById(String dbName, Integer id) {
		if (dbName.equals("emp")) {

			return jdbcTemplate1.queryForObject(SQL_FIND_ID, new Object[] { id }, new EmployeeMapper());
		} else {
			return jdbcTemplate2.queryForObject(SQL_FIND_ID, new Object[] { id }, new EmployeeMapper());

		}
	}

	@Override
	public Optional<Employee> getTokenbyDBName(String dbName, String mailId, String password) {
		if (dbName.equals("emp")) {
			return Optional.of(jdbcTemplate1.queryForObject(SQL_FIND_MAIL_PASSWORD_DB1,
					new Object[] { mailId, password }, new EmployeeMapper()));
		} else {
			return Optional.of(jdbcTemplate2.queryForObject(SQL_FIND_MAIL_PASSWORD_DB2,
					new Object[] { mailId, password }, new EmployeeMapper()));

		}

	}

}

package com.employee.employeedetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import com.employee.employeedetails.DTO.EmployeeDTO;
import com.employee.employeedetails.model.Employee;
import com.employee.employeedetails.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/id")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@RequestHeader("Authorization") String token, @RequestParam("dbName") String dbName,
			@RequestParam("id") Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		Employee emp = employeeService.getEmployeeDeatils(dbName, id);
		EmployeeDTO employeeDTO = new EmployeeDTO().builder().id(emp.getId()).name(emp.getName()).dob(emp.getDob())
				.mailId(emp.getMailId()).build();
		try {
			if (Optional.of(employeeDTO).isPresent()) {
				return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(employeeDTO, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			String stacktrace = org.apache.commons.lang3.exception.ExceptionUtils.getMessage(e);

			log.error("Error in Employee ID Method: " + stacktrace);
			return new ResponseEntity<>(employeeDTO, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/token")
	public ResponseEntity<String> getToken(@RequestParam("dbName") String dbName, @RequestParam("mailId") String mailId,
			@RequestParam("password") String password) {
		Optional<Employee> emp = employeeService.getDBToken(dbName, mailId, password);
		String token = "";
		try {
			if (emp.isPresent()) {
				token = getJWTToken(dbName);
			} else {
				token = "Mail Id and password are not matched in DB, bearer token unable to generate";
			}
		} catch (Exception e) {
			String stacktrace = org.apache.commons.lang3.exception.ExceptionUtils.getMessage(e);

			log.error("Error in Employee Token Method: " + stacktrace);
			return new ResponseEntity<>(token, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);

	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}

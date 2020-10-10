package com.employee.employeedetails;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class EmployeedetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeedetailsApplication.class, args);
	}
	/*
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties(prefix="spring.datasource") public DataSource
	 * primaryDataSource() { return DataSourceBuilder.create().build(); }
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix="spring.secondDatasource") public DataSource
	 * secondaryDataSource() { return DataSourceBuilder.create().build(); }
	 */

}

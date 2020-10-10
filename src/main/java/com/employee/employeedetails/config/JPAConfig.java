/*
 * package com.employee.employeedetails.config;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.boot.jdbc.DataSourceBuilder; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.Primary;
 * 
 * @Configuration public class JPAConfig {
 * 
 * @Bean(name = "h2DataSource") public DataSource h2DataSource() {
 * DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
 * dataSourceBuilder.driverClassName("org.h2.Driver");
 * dataSourceBuilder.url("jdbc:h2:mem:emp"); dataSourceBuilder.username("sa");
 * dataSourceBuilder.password("password"); return dataSourceBuilder.build(); }
 * 
 * @Bean(name = "HsqlDataSource")
 * 
 * @Primary public DataSource mySqlDataSource() { DataSourceBuilder
 * dataSourceBuilder = DataSourceBuilder.create();
 * dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver");
 * dataSourceBuilder.url("jdbc:hsqldb:mem:emp1;DB_CLOSE_DELAY=-1");
 * dataSourceBuilder.username("sa"); dataSourceBuilder.password("sa"); return
 * dataSourceBuilder.build(); }
 * 
 * }
 */
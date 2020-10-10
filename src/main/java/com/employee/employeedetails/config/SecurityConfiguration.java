package com.employee.employeedetails.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests().antMatchers("/*/**")
				.permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/*/*/*").permitAll().antMatchers("/**").permitAll()
				.antMatchers(HttpMethod.POST, "/token").permitAll().anyRequest().authenticated().and().csrf().disable();
		// .anyRequest().authenticated();

	}
}
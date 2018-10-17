package com.spring.data.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//This allows to put security on java methods by Spring security using the PreAuthorize annotation on methods or class
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("abhilash").password("{noop}admin").roles("USER")
		.and()
		.withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		super.configure(security);
		security.csrf().disable();
	}
}

package br.desafio.pitang.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/singup").permitAll()
			.antMatchers(HttpMethod.POST, "/singin").permitAll()
			.antMatchers(HttpMethod.GET, "/me").permitAll()
			 .antMatchers("/h2-console/**/**").permitAll()
			.anyRequest().denyAll();
			
	}
	
	
	  public void configure(WebSecurity web) throws Exception {
		    // Allow swagger to be accessed without authentication
		    web.ignoring().antMatchers("/v2/api-docs")//
		        .antMatchers("/swagger-resources/**")//
		        .antMatchers("/swagger-ui.html")//
		        .antMatchers("/configuration/**")//
		        .antMatchers("/webjars/**")//
		        .antMatchers("/public")
		        
		        // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
		        .and()
		        .ignoring()
		        .antMatchers("/h2-console/**/**");;
		  }
}

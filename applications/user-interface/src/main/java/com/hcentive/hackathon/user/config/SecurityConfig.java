package com.hcentive.hackathon.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("anubhav").password("123456").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("zuned").password("123456").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("nitin").password("123456").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and().formLogin().loginProcessingUrl("/login").usernameParameter("username").
            passwordParameter("password").and()
			.logout().logoutSuccessUrl("/login?logout=true");
            http.csrf().disable();
	}
	
}
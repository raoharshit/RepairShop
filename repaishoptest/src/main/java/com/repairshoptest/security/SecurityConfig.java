package com.repairshoptest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	@Autowired
	private CustomCorsConfiguration customCorsConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(customCorsConfiguration) // cors configuration
				.and().csrf().disable().authorizeHttpRequests()
				.antMatchers("/login", "/register").permitAll() // Allow public endpoints without authentication
				.anyRequest().authenticated() // Authenticate other endpoints
				.and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Register JWT
																									// filter
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
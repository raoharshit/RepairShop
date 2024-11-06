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
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	@Autowired
	private CustomCorsConfiguration customCorsConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(customCorsConfiguration)).csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests -> requests
                .antMatchers("/login", "/register","/forgotpassword","/forgotpassword/verifyotp","/forgotpassword/resendotp").permitAll() 
                .anyRequest().authenticated()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())  
                        .addLogoutHandler(new SecurityContextLogoutHandler())  
                        .addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID", "jwt"))  
                        .invalidateHttpSession(true)  
                        .clearAuthentication(true));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
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
                .antMatchers("/login", "/register").permitAll() // Allow public endpoints without authentication
                .anyRequest().authenticated()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL to trigger logout
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())  // Use custom logout handler
                        .addLogoutHandler(new SecurityContextLogoutHandler())  // Clear authentication
                        .addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID", "jwt"))  // Delete cookies
                        .invalidateHttpSession(true)  // Invalidate session
                        .clearAuthentication(true));  // Register JWT
																									// filter
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
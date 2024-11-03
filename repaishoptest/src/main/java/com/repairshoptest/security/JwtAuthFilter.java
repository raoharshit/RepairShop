package com.repairshoptest.security;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;
import com.repairshoptest.model.RepairPerson;
import com.repairshoptest.model.User;
import com.repairshoptest.service.UserService;
import com.repairshoptest.utils.JwtUtil;
import com.repairshoptest.utils.UserContextHolder;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    
    @Autowired JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Step 1: Extract JWT token from the Authorization header
    	System.out.println("in jwt");
    	String requestPath = request.getRequestURI();
        if (requestPath.equals("/login") || requestPath.equals("/register")) {
            filterChain.doFilter(request, response);
            return;
        }
    	String jwt = extractJwtFromRequest(request);
    	System.out.println(jwt);
        if (jwt != null && !jwtUtil.isTokenValid(jwt)) {
        	
            int userId = jwtUtil.extractUserId(jwt);
            User user = userService.findById(userId);
            
         // Step 4: Authorization logic based on user type and endpoint
            String requestUri = request.getRequestURI();
            if (requestUri.startsWith("/clerk") && !(user instanceof Clerk)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not have access to Clerk resources.");
                return;
            } else if (requestUri.startsWith("/customer") && !(user instanceof Customer)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not have access to Customer resources.");
                return;
            } else if (requestUri.startsWith("/repairperson") && !(user instanceof RepairPerson)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "User does not have access to Repair Person resources.");
                return;
            }
            
//            UserContextHolder.setUserId(Integer.toString(userId));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(Integer.toString(userId), null, new ArrayList<>());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized user");
        	return ;
        }

            

            // If all checks pass, continue the filter chain
            filterChain.doFilter(request, response);

        }
    
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
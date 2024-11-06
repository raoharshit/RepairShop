package com.repairshoptest.security;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
                                org.springframework.security.core.Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK); 
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Logout successful\"}");
        response.getWriter().flush();
    }
}
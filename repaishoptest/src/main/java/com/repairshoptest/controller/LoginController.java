package com.repairshoptest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repairshoptest.model.Customer;
import com.repairshoptest.model.User;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.utils.JwtUtil;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ClerkService clerkService;
    
    @Autowired
    private RepairPersonService repairPersonService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user (this is just a placeholder for actual authentication logic)
    	System.out.println("in login");
    	User user;
    	System.out.println(loginRequest.getType());
        if(loginRequest.getType().equals("Customer")) {
        	user = customerService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        }
        else if (loginRequest.getType().equals("Clerk")) {
        	user = clerkService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        }else if(loginRequest.getType().equals("RepairPerson")){
        	user = repairPersonService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());
        }else {
        	//throw new Exception("Invalid user type");
        	return null;
        }

        // Generate JWT token with user_id
        System.out.println(user.getId());
        if(user != null) {
        	String token = jwtUtil.generateToken(Integer.toString(user.getId()));
            System.out.println(token);
            // Return token in response
            return ResponseEntity.ok(token);
        }
        
        return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
    }
    
    

    // LoginRequest class (for request body)
    public static class LoginRequest {
        private String userName;
        private String password;
        private String type;
        
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
        
        
        // Getters and setters
    }
    
}
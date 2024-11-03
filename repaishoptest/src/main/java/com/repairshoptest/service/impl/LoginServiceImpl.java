package com.repairshoptest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairshoptest.dto.LoginRequest;
import com.repairshoptest.dto.LoginResponse;
import com.repairshoptest.model.User;
import com.repairshoptest.service.ClerkService;
import com.repairshoptest.service.CustomerService;
import com.repairshoptest.service.LoginService;
import com.repairshoptest.service.RepairPersonService;
import com.repairshoptest.utils.JwtUtil;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private RepairPersonService repairPersonService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public LoginResponse authenticateUser(LoginRequest loginRequest) {
		User user;
    	System.out.println(loginRequest.getType());
        if(loginRequest.getType().equals("Customer")) {
        	user = customerService.authenticateCustomer(loginRequest.getUserName(), loginRequest.getPassword());
        }
        else if (loginRequest.getType().equals("Clerk")) {
        	System.out.println("in clerk");
        	user = clerkService.authenticateClerk(loginRequest.getUserName(), loginRequest.getPassword());
        }else if(loginRequest.getType().equals("RepairPerson")){
        	user = repairPersonService.authenticateRepairPerson(loginRequest.getUserName(), loginRequest.getPassword());
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
            LoginResponse loginResponse = new LoginResponse("User authenticated successfully",token);
            return loginResponse;
        }
        //throw new Exception("User not found");
        return null;
	}

}

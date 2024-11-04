package com.repairshoptest.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class LoginRequest {
	    
	    @NotNull(message = "username cannot be null")
	    @NotBlank(message = "Username cannot be blank")
        private String userName;
	    
	    @NotNull(message = "password cannot be null")
	    @NotBlank(message = "Password cannot be blank")
        private String password;
	    
	    @NotNull(message = "type cannot be null")
	    @NotBlank(message = "type cannot be blank")
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
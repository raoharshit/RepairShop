package com.repairshoptest.dto;
public class LoginRequest {
	
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
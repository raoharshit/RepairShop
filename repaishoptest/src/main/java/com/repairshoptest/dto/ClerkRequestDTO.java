package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.model.Customer;

public class ClerkRequestDTO {
	
	@NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
	private String name;
	
	@NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
	private String email;
	
	@NotNull(message = "phone cannot be null")
    @NotBlank(message = "phone cannot be blank")
	private String phone;
	
	@NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be blank")
	private String address;
	
	private String password;
	
	
	public ClerkRequestDTO() {
		super();
	}


	public ClerkRequestDTO(String name, String email, String phone, String address,String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "ClerkDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", password=" + password + "]";
	}
	
	public Clerk getClerk() {
		  Clerk clerk = new Clerk();
		    clerk.setName(this.name);
	        clerk.setEmail(this.email);
	        clerk.setPhone(this.phone);
	        clerk.setAddress(address);
	        return clerk;
	}
}

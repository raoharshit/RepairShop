package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
	
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
	
	@NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
	private String password;
	
	@NotNull(message = "type cannot be null")
    @NotBlank(message = "type cannot be blank")
	private String type;
	
	private String specialty;
	
	
	public RegisterRequest() {
		super();
	}


	public RegisterRequest(String name, String email, String phone, String address, String password, String type,
			String specialty) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.type = type;
		this.specialty = specialty;
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


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public ClerkRequestDTO getClerk() {
		ClerkRequestDTO clerkRequestDTO = new ClerkRequestDTO();
		clerkRequestDTO.setName(this.name);
		clerkRequestDTO.setEmail(this.email);
		clerkRequestDTO.setPhone(this.phone);
		clerkRequestDTO.setAddress(this.address);
		clerkRequestDTO.setPassword(this.password);
		return clerkRequestDTO;
	}
	
	public RepairPersonRequestDTO getRepairPerson() {
		RepairPersonRequestDTO repairPersonDTO = new RepairPersonRequestDTO();
		repairPersonDTO.setName(this.name);
		repairPersonDTO.setEmail(this.email);
		repairPersonDTO.setPhone(this.phone);
		repairPersonDTO.setAddress(this.address);
		repairPersonDTO.setPassword(this.password);
		repairPersonDTO.setSpecialty(this.specialty);
		return repairPersonDTO;
	}

}

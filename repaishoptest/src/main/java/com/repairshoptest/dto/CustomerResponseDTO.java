package com.repairshoptest.dto;

import com.repairshoptest.model.Customer;

public class CustomerResponseDTO {
	
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	public CustomerResponseDTO() {
		super();
	}

	public CustomerResponseDTO(int id,String name, String email, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "CustomerResponseDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ "]";
	}
	
	public static CustomerResponseDTO fromEntity(Customer customer) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setId(customer.getId());
		customerResponseDTO.setName(customer.getName());
		customerResponseDTO.setEmail(customer.getEmail());
		customerResponseDTO.setPhone(customer.getPhone());
		customerResponseDTO.setAddress(customer.getAddress());
		
		return customerResponseDTO;
	}

}

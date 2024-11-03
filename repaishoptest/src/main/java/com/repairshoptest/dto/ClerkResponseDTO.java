package com.repairshoptest.dto;

import com.repairshoptest.model.Clerk;

public class ClerkResponseDTO {
	
	private int id;
	private String name;
	private String phone;
	private String email;
	private String address;
	
	
	public ClerkResponseDTO() {
		super();
	}


	public ClerkResponseDTO(int id, String name, String phone, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "ClerkResponseDTO [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address="
				+ address + "]";
	}
	
	public static ClerkResponseDTO fromEntity(Clerk clerk) {
		ClerkResponseDTO dto = new ClerkResponseDTO();
		dto.setId(clerk.getId());
		dto.setName(clerk.getName());
		dto.setPhone(clerk.getPhone());
		dto.setEmail(clerk.getEmail());
		dto.setAddress(clerk.getAddress());
		
		return dto;
	}

}

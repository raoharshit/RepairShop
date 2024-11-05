package com.repairshoptest.dto;

import com.repairshoptest.model.User;

public class UserResponseDTO {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String type;

	public UserResponseDTO(int id, String name, String email, String phone, String address, String type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.type = type;
	}

	public UserResponseDTO() {
		super();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static UserResponseDTO fromEntity(User user) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(user.getId());
		userResponseDTO.setName(user.getName());
		userResponseDTO.setEmail(user.getEmail());
		userResponseDTO.setPhone(user.getPhone());
		userResponseDTO.setAddress(user.getAddress());
		userResponseDTO.setType(user.getType());

		return userResponseDTO;
	}

}

package com.repairshoptest.dto;

import com.repairshoptest.model.RepairPerson;

public class RepairPersonResponseDTO {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String speciality;
	
	public RepairPersonResponseDTO() {
		super();
	}

	public RepairPersonResponseDTO(int id, String name, String email, String phone, String address, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.speciality = speciality;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "RepairPersonRespnseDTO [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", speciality=" + speciality + "]";
	}
	
	public static RepairPersonResponseDTO fromEntity(RepairPerson repairPerson) {
		RepairPersonResponseDTO dto = new RepairPersonResponseDTO();
		dto.setId(repairPerson.getId());
		dto.setName(repairPerson.getName());
		dto.setEmail(repairPerson.getEmail());
		dto.setPhone(repairPerson.getPhone());
		dto.setAddress(repairPerson.getAddress());
		dto.setSpeciality(repairPerson.getSpeciality());
		
		return dto;
		
		
	}
	
}

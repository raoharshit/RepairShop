package com.repairshoptest.dto;

import com.repairshoptest.model.RepairPerson;

public class RepairPersonRequestDTO {
	
	private String name;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String specialty;
	
	
	


	public RepairPersonRequestDTO() {
		super();
	}


	public RepairPersonRequestDTO(String name, String email, String phone, String address,String password, String specialty) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
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


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "RepairPersonRequestDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", password=" + password + ", speciality=" + specialty + "]";
	}
	
	public RepairPerson getRepairPerson() {
		RepairPerson repairPerson = new RepairPerson();
		repairPerson.setName(this.name);
		repairPerson.setEmail(this.email);
		repairPerson.setPhone(this.phone);
		repairPerson.setAddress(this.address);
		repairPerson.setSpecialty(specialty);
	    return repairPerson;
	}
}

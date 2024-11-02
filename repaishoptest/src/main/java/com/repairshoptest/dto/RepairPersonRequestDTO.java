package com.repairshoptest.dto;

import com.repairshoptest.model.RepairPerson;

public class RepairPersonRequestDTO {
	
	private String name;
	private String email;
	private String phone;
	private String address;
	private String speciality;
	
	
	


	public RepairPersonRequestDTO() {
		super();
	}


	public RepairPersonRequestDTO(String name, String email, String phone, String address, String speciality) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.speciality = speciality;
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
		return "RepairPersonDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", speciality=" + speciality + "]";
	}
	
	public RepairPerson getRepairPerson() {
		RepairPerson repairPerson = new RepairPerson();
		repairPerson.setName(this.name);
		repairPerson.setEmail(this.email);
		repairPerson.setPhone(this.phone);
		repairPerson.setAddress(address);
		repairPerson.setSpeciality(speciality);
	    return repairPerson;
	}
}

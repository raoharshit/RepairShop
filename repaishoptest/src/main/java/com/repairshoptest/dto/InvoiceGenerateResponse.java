package com.repairshoptest.dto;

public class InvoiceGenerateResponse {
	
	private int id;
	
	private String message;

	public InvoiceGenerateResponse() {
		super();
	}

	public InvoiceGenerateResponse(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

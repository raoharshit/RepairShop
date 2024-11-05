package com.repairshoptest.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.repairshoptest.model.Invoice;

public class InvoiceResponse {
	
	private int id;
	private Double totalAmount;
	private LocalDateTime delieveredAt;
	private RepairServiceResponseDTO serviceDTO;
	private List<AdditionalItemRFAResponseDTO> list;
	
	public InvoiceResponse() {
		super();
	}

	public InvoiceResponse(int id, Double totalAmount, LocalDateTime delieveredAt, RepairServiceResponseDTO serviceDTO,
			List<AdditionalItemRFAResponseDTO> list) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.delieveredAt = delieveredAt;
		this.serviceDTO = serviceDTO;
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getDelieveredAt() {
		return delieveredAt;
	}

	public void setDelieveredAt(LocalDateTime delieveredAt) {
		this.delieveredAt = delieveredAt;
	}

	public RepairServiceResponseDTO getServiceDTO() {
		return serviceDTO;
	}

	public void setServiceDTO(RepairServiceResponseDTO serviceDTO) {
		this.serviceDTO = serviceDTO;
	}

	public List<AdditionalItemRFAResponseDTO> getList() {
		return list;
	}

	public void setList(List<AdditionalItemRFAResponseDTO> list) {
		this.list = list;
	}
	
	public static InvoiceResponse getInvoiceResponse(Invoice invoice) {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		invoiceResponse.setId(invoice.getId());
		invoiceResponse.setTotalAmount(invoice.getTotalAmount());
		invoiceResponse.setDelieveredAt(invoice.getDeliveredAt());
		invoiceResponse.setServiceDTO(RepairServiceResponseDTO.fromEntity(invoice.getRepairService()));
		return invoiceResponse;
	}
	
	

}

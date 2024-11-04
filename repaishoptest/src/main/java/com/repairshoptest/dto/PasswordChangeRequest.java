package com.repairshoptest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PasswordChangeRequest {
	
	@NotNull(message = "oldpassword cannot be null")
    @NotBlank(message = "oldpassword cannot be blank")
	private String oldPassword;
	
	@NotNull(message = "newpassword cannot be null")
    @NotBlank(message = "newpassword cannot be blank")
	private String newPassword;
	
	public PasswordChangeRequest() {
		super();
	}

	public PasswordChangeRequest(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}

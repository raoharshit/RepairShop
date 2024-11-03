package com.repairshoptest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
	private String errorCode;
	private String errorMessage;
}

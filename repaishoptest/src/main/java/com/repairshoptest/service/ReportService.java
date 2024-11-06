package com.repairshoptest.service;

import com.repairshoptest.dto.ReportResponse;

public interface ReportService {
	
	ReportResponse getMonthlyReport(int year, int month);

}

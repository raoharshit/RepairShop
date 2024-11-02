package com.repairshoptest.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ServiceCodeGenerator {

    public static String generateServiceCode() {
        // Prefix for the service code
        String prefix = "SRV-";
        
        // Date part (e.g., "20231030" for October 30, 2024)
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        
        // Random 4-digit number
        int randomPart = new Random().nextInt(9000) + 1000; // Generates a number between 1000 and 9999
        
        // Combine parts to form the service code
        return prefix + datePart + "-" + randomPart;
    }
}
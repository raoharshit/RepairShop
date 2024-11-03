package com.repairshoptest.request;

import com.repairshoptest.dto.CustomError;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;
    private String type;
    private CustomError error;
}

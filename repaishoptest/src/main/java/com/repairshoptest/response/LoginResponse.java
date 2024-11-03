package com.repairshoptest.response;

import com.repairshoptest.dto.CustomError;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String userName;
    private String token;
    private CustomError error;
}

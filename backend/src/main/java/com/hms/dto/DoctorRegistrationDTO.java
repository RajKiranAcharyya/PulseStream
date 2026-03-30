package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRegistrationDTO {
    private String username;
    private String email;
    private String password;
    private String spec;
    private Integer docFees;
}

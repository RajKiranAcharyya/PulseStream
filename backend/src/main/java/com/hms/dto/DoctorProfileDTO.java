package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {
    private String username;
    private String spec;
    private Integer docFees;
    private String password;
}

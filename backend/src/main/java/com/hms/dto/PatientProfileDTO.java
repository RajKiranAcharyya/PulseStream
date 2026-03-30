package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDTO {
    private String fname;
    private String lname;
    private String contact;
    private String password;
}

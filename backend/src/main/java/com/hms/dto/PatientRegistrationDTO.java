package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegistrationDTO {
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String contact;
    private String password;
}

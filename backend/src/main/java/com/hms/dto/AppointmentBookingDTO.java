package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBookingDTO {
    private String doctor;
    private LocalDate appdate;
    private LocalTime apptime;
    private String disease;
    private Integer age;
    private Integer docFees;
}

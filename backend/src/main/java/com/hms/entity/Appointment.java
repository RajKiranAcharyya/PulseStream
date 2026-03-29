package com.hms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "appointmenttb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE appointmenttb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean deleted = false;
    
    private Long pid;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String contact;
    private String doctor;
    private Integer docFees;
    
    private Integer age;
    private String disease;
    
    private LocalDate appdate;
    private LocalTime apptime;
    
    private Integer userStatus; // 1: Active, 0: Cancelled
    private Integer doctorStatus; // 1: Active, 0: Cancelled, 2: Prescribed
}

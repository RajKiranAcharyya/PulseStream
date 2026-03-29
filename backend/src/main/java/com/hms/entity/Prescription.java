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
@Table(name = "prestb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE prestb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private boolean deleted = false;
    
    private Long appointmentId;
    private String doctor;
    private Long pid;
    private String fname;
    private String lname;
    private LocalDate appdate;
    private LocalTime apptime;
    private String disease;
    private String prescription;
}

package com.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "doctb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE doctb SET deleted = true WHERE email = ?")
@Where(clause = "deleted = false")
public class Doctor {
    @Id
    private String email; 
    
    private boolean deleted = false;
    
    private String username;
    private String password;
    private String spec;
    private Integer docFees;
}

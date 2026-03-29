package com.hms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE availability SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String doctorEmail;
    private String dayOfWeek; // e.g., Monday, Tuesday
    private LocalTime startTime;
    private LocalTime endTime;
    
    private boolean deleted = false;
}

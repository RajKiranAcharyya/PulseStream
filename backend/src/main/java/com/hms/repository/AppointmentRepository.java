package com.hms.repository;

import com.hms.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPid(Long pid);
    List<Appointment> findByDoctor(String doctor);
    List<Appointment> findByContact(String contact);
    List<Appointment> findByEmail(String email);
    
    @org.springframework.data.jpa.repository.Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor = :doctor AND a.appdate = :date AND a.apptime = :time AND a.userStatus != 0 AND a.doctorStatus != 0")
    boolean isSlotOccupied(@org.springframework.data.repository.query.Param("doctor") String doctor, @org.springframework.data.repository.query.Param("date") java.time.LocalDate date, @org.springframework.data.repository.query.Param("time") java.time.LocalTime time);
    
    List<Appointment> findByAppdateAndUserStatusAndDoctorStatus(java.time.LocalDate date, Integer userStatus, Integer doctorStatus);
}

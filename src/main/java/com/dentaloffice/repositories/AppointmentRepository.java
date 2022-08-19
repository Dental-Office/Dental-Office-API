package com.dentaloffice.repositories;

import com.dentaloffice.models.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("SELECT a FROM Appointment a WHERE a.date LIKE %?1%"
            + " OR a.time LIKE %?2%")
    Page<Appointment> findByFiltering(String filter, String date, String time, Pageable pageable);
}

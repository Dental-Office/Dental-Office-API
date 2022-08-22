package com.dentaloffice.repositories;

import com.dentaloffice.models.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Column(name="first_name, last_name")
    Page<Appointment> findByPatientFirstNameContainingOrPatientLastNameContaining(String firstName, String lastName, Pageable pageable);

}

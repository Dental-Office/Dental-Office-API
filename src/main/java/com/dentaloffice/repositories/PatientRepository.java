package com.dentaloffice.repositories;

import com.dentaloffice.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %?1%"
            + " OR p.lastName LIKE %?2%"
            + " OR p.birthDate LIKE %?3%"
            + " OR p.phoneNumber LIKE %?4%")
    Page<Patient> findByFiltering(String firstName, String lastName, String dateOfBirth, String phoneNumber, Pageable pageable);
}

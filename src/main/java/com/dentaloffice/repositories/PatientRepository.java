package com.dentaloffice.repositories;

import com.dentaloffice.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %?1%"
            + " OR p.lastName LIKE %?1%"
            + " OR p.dateOfBirth LIKE %?1%"
            + " OR p.phoneNumber LIKE %?1%")
    Page<Patient> findByFiltering(String firstName, String lastName, String dateOfBirth, String phoneNumber, Pageable pageable);
}

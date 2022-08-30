package com.dentaloffice.repositories;

import com.dentaloffice.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query("SELECT p FROM Patient p WHERE LOWER(p.firstName) LIKE %?1%"
            + " OR LOWER(p.lastName) LIKE %?1%")
    Page<Patient> findByFiltering(String firstName, String lastName, Pageable pageable);
}

package com.dentaloffice.repositories;

import com.dentaloffice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %?1%"
            + " OR p.lastName LIKE %?1%"
            + " OR p.dateOfBirth LIKE %?1%"
            + " OR p.phoneNumber LIKE %?1%")
    List<Patient> findByFiltering(String firstName, String lastName, String dateOfBirth, String phoneNumber);

}

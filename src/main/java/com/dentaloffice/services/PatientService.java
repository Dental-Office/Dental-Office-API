package com.dentaloffice.services;

import com.dentaloffice.models.Patient;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PatientService {

    Patient save(Patient patient);

    Page<Patient> findAll(String filter, Integer pageNo, Integer pageSize, String sortKey);

    void delete(UUID id);

    Patient edit(Patient editedPatient);

    Patient get(UUID id);

    boolean exists(UUID id);
}

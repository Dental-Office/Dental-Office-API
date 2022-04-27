package com.dentaloffice.services;

import com.dentaloffice.models.Patient;

import java.util.List;

public interface PatientService {

    Patient save(Patient patient);

    List<Patient> findAll();

    void delete(Long id);

    Patient edit(Patient editedPatient);

    Patient get(Long id);

    boolean exists(Long id);
}

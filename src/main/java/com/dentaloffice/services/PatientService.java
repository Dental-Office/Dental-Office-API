package com.dentaloffice.services;

import com.dentaloffice.models.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);

    List<Patient> findAllPatients();

    Patient findPatientById(Long id);

    void delete(Long id);

    Patient edit (Long id, Patient editedPatient);

    Patient getPatient(Long id);
}

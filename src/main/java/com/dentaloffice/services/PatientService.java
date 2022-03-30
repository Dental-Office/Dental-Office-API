package com.dentaloffice.services;

import com.dentaloffice.models.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);

    List<Patient> findAllPatients();
}

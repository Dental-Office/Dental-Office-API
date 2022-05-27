package com.dentaloffice.services;

import com.dentaloffice.dto.PatientsResponseDTO;
import com.dentaloffice.models.Patient;

import java.util.UUID;

public interface PatientService {

    Patient save(Patient patient);

    PatientsResponseDTO findAll(String filter, Integer pageNo, Integer pageSize, String sortKey);

    PatientsResponseDTO findAll();

    void delete(UUID id);

    Patient edit(Patient editedPatient);

    Patient get(UUID id);

    boolean exists(UUID id);
}

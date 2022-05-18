package com.dentaloffice.services;

import com.dentaloffice.dto.PatientsResponseDTO;
import com.dentaloffice.models.Patient;

public interface PatientService {

    Patient save(Patient patient);

    PatientsResponseDTO findAll(String filter, Integer pageNo, Integer pageSize, String sortKey);

    PatientsResponseDTO findAll();

    void delete(Long id);

    Patient edit(Patient editedPatient);

    Patient get(Long id);

    boolean exists(Long id);
}

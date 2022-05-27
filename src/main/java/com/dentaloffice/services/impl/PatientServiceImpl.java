package com.dentaloffice.services.impl;

import com.dentaloffice.dto.PatientsResponseDTO;
import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public PatientsResponseDTO findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        Page<Patient> pagedResult = patientRepository.findByFiltering(filter, filter, filter, filter,  pageable);

        PatientsResponseDTO response = new PatientsResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @Override
    public PatientsResponseDTO findAll(){
        PatientsResponseDTO response = new PatientsResponseDTO();

        response.setContent(patientRepository.findAll());

        return response;
    }

    public boolean exists(UUID id){
        return patientRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient edit(Patient editedPatient) {
        return patientRepository.save(editedPatient);
    }

    @Override
    public Patient get(UUID id) {
        return patientRepository.getById(id);
    }
}

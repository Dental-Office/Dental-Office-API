package com.dentaloffice.services.impl;

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
    public Page<Patient> findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        return patientRepository.findByFiltering(filter, filter, filter, filter, pageable);
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

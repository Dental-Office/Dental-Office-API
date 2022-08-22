package com.dentaloffice.services.impl;
import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Page<Patient> pagedResult = patientRepository.findByFiltering(filter, filter, pageable);

        List<Patient> persistedPatients = pagedResult.getContent();
        List<Patient> patients = persistedPatients.stream()
                .map(item -> new Patient(item.getId(), item.getFirstName(), item.getLastName(), item.getBirthDate(), item.getPhoneNumber(), item.getRecords()))
                .collect(Collectors.toList());

        return new PageImpl<>(patients, pagedResult.getPageable(), pagedResult.getTotalElements());
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

        Patient patientDb = patientRepository.getById(id);

        return new Patient(patientDb.getId(), patientDb.getFirstName(), patientDb.getLastName(), patientDb.getBirthDate(), patientDb.getPhoneNumber(), patientDb.getRecords());
    }
}

package com.dentaloffice.services;

import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public boolean exists(Long id){
        return patientRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient edit(Patient editedPatient) {
        return patientRepository.save(editedPatient);
    }

    @Override
    public Patient get(Long id) {
        return patientRepository.getById(id);
    }
}

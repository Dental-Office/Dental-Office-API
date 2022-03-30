package com.dentaloffice.services;

import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientImpl implements PatientService {

    public final PatientRepository patientRepository;

    public PatientImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAllPatients(){
        return patientRepository.findAll();
    }
}

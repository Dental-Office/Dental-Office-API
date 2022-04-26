package com.dentaloffice.services;

import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    public final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
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
    @Override
    public Patient findPatientById (Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + id + " doesn't exist");
        }
        return patientRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public Patient edit(Long id, Patient editedPatient) {
        Patient patient = findPatientById(id);

        patient.setFirstName(editedPatient.getFirstName());
        patient.setLastName(editedPatient.getLastName());
        patient.setDateOfBirth(editedPatient.getDateOfBirth());
        patient.setPhoneNumber(editedPatient.getPhoneNumber());

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.getById(id);
    }
}

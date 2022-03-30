package com.dentaloffice.controllers;

import com.dentaloffice.models.Patient;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(PatientController.BASE_URL)
@AllArgsConstructor
public class PatientController {

    static final String BASE_URL = "/patient";
    private final PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAllPatients();
    }

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
}

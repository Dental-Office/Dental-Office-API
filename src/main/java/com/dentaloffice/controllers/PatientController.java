package com.dentaloffice.controllers;

import com.dentaloffice.models.Patient;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Patient savePatient(@Valid @RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

    @GetMapping("{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @PutMapping("{id}")
    public Patient editPatient(@PathVariable Long id, @Valid @RequestBody Patient patient) {
        return patientService.edit(id, patient);
    }
}

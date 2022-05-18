package com.dentaloffice.controllers;

import com.dentaloffice.dto.PatientRequestDTO;
import com.dentaloffice.dto.PatientsResponseDTO;
import com.dentaloffice.models.Patient;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(PatientController.BASE_URL)
@AllArgsConstructor
public class PatientController {

    public static final String BASE_URL = "/patient";
    private final PatientService patientService;

    @GetMapping
    public PatientsResponseDTO findAll(@RequestParam(name="searchTerm", required = false) String searchTerm,
                                       @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(name = "sort", defaultValue = "id", required = false) String sort) {
        if (searchTerm != null || !searchTerm.isBlank()) {
            return patientService.findAll(searchTerm, pageNo, pageSize, sort);
        } else {
            return patientService.findAll();
        }
    }

    @PostMapping
    public Patient save(@Valid @RequestBody PatientRequestDTO patient) {
        Patient patientToBeSaved = new Patient();
        patientToBeSaved.setFirstName(patient.getFirstName());
        patientToBeSaved.setLastName(patient.getLastName());
        patientToBeSaved.setDateOfBirth(patient.getDateOfBirth());
        patientToBeSaved.setPhoneNumber(patient.getPhoneNumber());
        return patientService.save(patientToBeSaved);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (!patientService.exists(id)) {
            throwNotFoundException(id);
        }
        patientService.delete(id);
    }

    @GetMapping("{id}")
    public Patient get(@PathVariable Long id) {
        return patientService.get(id);
    }

    @PutMapping("{id}")
    public Patient edit(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO patient) {
        Patient patientToBeSaved = new Patient();
        patientToBeSaved.setId(id);
        patientToBeSaved.setFirstName(patient.getFirstName());
        patientToBeSaved.setLastName(patient.getLastName());
        patientToBeSaved.setDateOfBirth(patient.getDateOfBirth());
        patientToBeSaved.setPhoneNumber(patient.getPhoneNumber());

        if (!patientService.exists(id)) {
            throwNotFoundException(id);
        }

        return patientService.edit(patientToBeSaved);
    }

    private void throwNotFoundException(Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

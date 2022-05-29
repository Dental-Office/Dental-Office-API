package com.dentaloffice.controllers;

import com.dentaloffice.dto.PatientRequestDTO;
import com.dentaloffice.dto.PatientsResponseDTO;
import com.dentaloffice.models.Patient;
import com.dentaloffice.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(PatientController.BASE_URL)
@AllArgsConstructor
public class PatientController {

    public static final String BASE_URL = "/patient";
    private final PatientService patientService;

    @GetMapping
    public PatientsResponseDTO findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                       @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(name = "sort", defaultValue = "firstName", required = false) String sort) {
        Page<Patient> pagedResult = patientService.findAll(searchTerm, pageNo, pageSize, sort);

        PatientsResponseDTO response = new PatientsResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @PostMapping
    public Patient save(@Valid @RequestBody PatientRequestDTO patient) {
        Patient patientToBeSaved = new Patient();
        patientToBeSaved.setFirstName(patient.getFirstName());
        patientToBeSaved.setLastName(patient.getLastName());
        patientToBeSaved.setBirthDate(patient.getBirthDate());
        patientToBeSaved.setPhoneNumber(patient.getPhoneNumber());
        return patientService.save(patientToBeSaved);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        if (!patientService.exists(id)) {
            throwNotFoundException(id);
        }
        patientService.delete(id);
    }

    @GetMapping("{id}")
    public Patient get(@PathVariable UUID id) {
        return patientService.get(id);
    }

    @PutMapping("{id}")
    public Patient edit(@PathVariable UUID id, @Valid @RequestBody PatientRequestDTO patient) {
        Patient patientToBeSaved = new Patient();
        patientToBeSaved.setId(id);
        patientToBeSaved.setFirstName(patient.getFirstName());
        patientToBeSaved.setLastName(patient.getLastName());
        patientToBeSaved.setBirthDate(patient.getBirthDate());
        patientToBeSaved.setPhoneNumber(patient.getPhoneNumber());

        if (!patientService.exists(id)) {
            throwNotFoundException(id);
        }

        return patientService.edit(patientToBeSaved);
    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

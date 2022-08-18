package com.dentaloffice.controllers;

import com.dentaloffice.dto.RecordRequestDTO;
import com.dentaloffice.dto.RecordResponseDTO;
import com.dentaloffice.models.Patient;
import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.PatientRepository;
import com.dentaloffice.repositories.RecordRepository;
import com.dentaloffice.services.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(RecordController.BASE_URL)
@AllArgsConstructor
public class RecordController {

    public static final String BASE_URL = "/record";
    private final RecordService recordService;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    public Record save(@Valid @RequestBody RecordRequestDTO record) {
        Record recordToBeSaved = new Record();
        Patient patient = new Patient();
        patient.setId(UUID.fromString(record.getPatientId()));
        recordToBeSaved.setPatient(patient);

        return recordService.save(recordToBeSaved);
    }

    @GetMapping
    public RecordResponseDTO findAll(@RequestParam(name="searchTerm", required = false) String searchTerm,
                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Record> pagedResult = recordService.findAll(searchTerm, pageNo, pageSize);

        RecordResponseDTO response = new RecordResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @GetMapping("{id}")
    public Record get(@PathVariable UUID id) {
        return recordService.get(id);
    }

    @PutMapping("{id}")
    public Record edit(@PathVariable UUID id, @Valid @RequestBody RecordRequestDTO record) {
        Record recordToBeSaved = new Record();
        Patient patient = new Patient();
        patient.setId(UUID.fromString(record.getPatientId()));
        recordToBeSaved.setPatient(patient);

        if (!recordService.exists(id)) {
            throwNotFoundException(id);
        }

        return recordService.edit(recordToBeSaved);
    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }


    @PutMapping("/{recordId}/patient/{patientId}")Record assignPatientToRecord(@PathVariable UUID recordId, @PathVariable UUID patientId){
        Record record = recordRepository.findById(recordId).get();
        Patient patient = patientRepository.findById(patientId).get();
        record.setPatient(patient);
        return recordRepository.save(record);
    }
}

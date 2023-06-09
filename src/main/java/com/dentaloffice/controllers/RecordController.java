package com.dentaloffice.controllers;

import com.dentaloffice.dto.MaterialRequestDTO;
import com.dentaloffice.dto.PageResponse;
import com.dentaloffice.dto.RecordRequestDTO;
import com.dentaloffice.models.Material;
import com.dentaloffice.models.Patient;
import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.PatientRepository;
import com.dentaloffice.repositories.RecordRepository;
import com.dentaloffice.services.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
        UUID patientId = UUID.fromString(record.getPatientId());

        return recordService.save(patientId, record.getMaterialIds());
    }

    @GetMapping
    public PageResponse<Record> findAll(@RequestParam(name="searchTerm", required = false) String searchTerm,
                                        @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Record> pagedResult = recordService.findAll(searchTerm, pageNo, pageSize);

        PageResponse<Record> response = new PageResponse<>();

        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @GetMapping("{id}")
    @Transactional
    public Record get(@PathVariable UUID id) {
        return recordService.get(id);
    }

//    @PutMapping("{id}")
//    public Record edit(@PathVariable UUID id, @Valid @RequestBody RecordRequestDTO record) {
//        Record recordToBeSaved = new Record();
//        Patient patient = new Patient();
//        patient.setId(UUID.fromString(record.getPatientId()));
//        recordToBeSaved.setPatient(patient);
//
//        if (!recordService.exists(id)) {
//            throwNotFoundException(id);
//        }
//
//        return recordService.edit(recordToBeSaved);
//    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }


    @PutMapping("/{recordId}/patient/{patientId}")Record assignPatientToRecord(@PathVariable UUID recordId, @Valid @RequestBody RecordRequestDTO record){
        Record recordToBeSaved = new Record();
        Patient patient = new Patient();
        patient.setId(UUID.fromString(record.getPatientId()));

        recordToBeSaved.setPatient(patient);

        if (!recordService.exists(recordId)) {
            throwNotFoundException(recordId);
        }

        return recordService.edit(recordToBeSaved);
    }
}

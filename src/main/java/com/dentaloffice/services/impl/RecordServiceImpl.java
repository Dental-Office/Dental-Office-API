package com.dentaloffice.services.impl;

import com.dentaloffice.models.Material;
import com.dentaloffice.models.Patient;
import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.RecordRepository;

import com.dentaloffice.services.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Override
    public Record save(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Page<Record> findAll(String filter, Integer pageNo, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Record> pagedResult = recordRepository.findByFiltering(filter, pageable);

        List<Record> persistedRecords = pagedResult.getContent();
        List<Record> records = persistedRecords.stream()
                .map(item -> {
                    Patient patient = item.getPatient();
                    Patient patientCopy = new Patient(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getBirthDate(), patient.getPhoneNumber(), null);
                    List<Material> material = item.getMaterials();
                    List<Material> materials = material.stream().map(itemM -> new Material(itemM.getId(), null, itemM.getMaterialName(), itemM.getQuantity())).collect(Collectors.toList());
                    return new Record(item.getId(), patientCopy, materials);
                })
                .collect(Collectors.toList());

        return new PageImpl<>(records, pagedResult.getPageable(), pagedResult.getTotalElements());
    }

    public boolean exists(UUID id){
        return recordRepository.existsById(id);
    }

    @Override
    public Record get(UUID id) {
        return recordRepository.getById(id);
    }

    @Override
    public Record edit(Record editedRecord) {
        return recordRepository.save(editedRecord);
    }
}

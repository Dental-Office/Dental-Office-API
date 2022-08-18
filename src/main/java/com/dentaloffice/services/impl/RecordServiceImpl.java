package com.dentaloffice.services.impl;

import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.RecordRepository;

import com.dentaloffice.services.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        return recordRepository.findByFiltering(filter, pageable);
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

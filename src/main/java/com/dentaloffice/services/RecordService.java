package com.dentaloffice.services;

import com.dentaloffice.models.Record;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface RecordService {

    Record save(UUID patientId, List<UUID> materialIds);

    Page<Record> findAll(String searchTerm, Integer pageNo, Integer pageSize);

    boolean exists(UUID id);

    Record get(UUID id);

    Record edit(Record editedRecord);

    Record assignPatientToRecord(UUID recordId, UUID patientId);
}

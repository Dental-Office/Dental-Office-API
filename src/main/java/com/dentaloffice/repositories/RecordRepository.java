package com.dentaloffice.repositories;

import com.dentaloffice.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

    @Query("SELECT r FROM Record r")
    Page<Record> findByFiltering(String filter, Pageable pageable);

    Optional<Record> findByIdAndMaterialsId(UUID recordId, UUID materialId);
}

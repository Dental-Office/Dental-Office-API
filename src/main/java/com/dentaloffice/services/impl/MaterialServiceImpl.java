package com.dentaloffice.services.impl;

import com.dentaloffice.models.Material;
import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.MaterialRepository;
import com.dentaloffice.repositories.RecordRepository;
import com.dentaloffice.services.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final RecordRepository recordRepository;

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Page<Material> findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        Page<Material> pagedResult = materialRepository.findByMaterialNameContainingOrQuantityContaining(filter, filter, pageable);

        List<Material> persistedMaterials = pagedResult.getContent();
        List<Material> materials = persistedMaterials.stream()
                .map(item -> new Material(item.getId(), item.getEnrolledRecords(), item.getMaterialName(), item.getQuantity()))
                .collect(Collectors.toList());

        return new PageImpl<>(materials, pagedResult.getPageable(), pagedResult.getTotalElements());
    }

    public boolean exists(UUID id) {
        return materialRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        materialRepository.deleteById(id);
    }

    @Override
    public Material edit(Material editedMaterial) {
        return materialRepository.save(editedMaterial);
    }

    @Override
    public Material get(UUID id) {
        Material materialDb = materialRepository.getById(id);

        return new Material(materialDb.getId(), materialDb.getEnrolledRecords(), materialDb.getMaterialName(), materialDb.getQuantity());
    }

    @Override
    @Transactional
    public void addRecord(UUID materialId, UUID recordId) throws Exception {
        Material material = materialRepository.getById(materialId);
        List<Record> enrolledRecords = material.getEnrolledRecords();
        Record record = recordRepository.getById(recordId);

        if (recordRepository.findByIdAndMaterialsId(recordId, materialId).isEmpty()) {
            enrolledRecords.add(record);
        } else {
            throw new Exception();
        }
    }

}

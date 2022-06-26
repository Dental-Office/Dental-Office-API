package com.dentaloffice.services.impl;

import com.dentaloffice.models.Material;
import com.dentaloffice.repositories.MaterialRepository;
import com.dentaloffice.services.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Page<Material> findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        return materialRepository.findByFiltering(filter, filter, pageable);
    }

    public boolean exists(UUID id){
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
        return materialRepository.getById(id);
    }
}

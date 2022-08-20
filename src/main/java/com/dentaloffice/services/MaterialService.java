package com.dentaloffice.services;

import com.dentaloffice.models.Material;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MaterialService {

    Material save(Material material);

    Page<Material> findAll(String filter, Integer pageNo, Integer pageSize, String sortKey);

    void delete(UUID id);

    Material edit(Material editedMaterial);

    Material get(UUID id);

    boolean exists(UUID id);
}

package com.dentaloffice.repositories;

import com.dentaloffice.models.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, UUID> {

    Page<Material> findByMaterialNameContainingOrQuantityContaining(String materialName, String quantity, Pageable pageable);
}

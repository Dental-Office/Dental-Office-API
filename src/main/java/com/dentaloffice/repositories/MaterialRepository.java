package com.dentaloffice.repositories;

import com.dentaloffice.models.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, UUID> {

    @Query("SELECT m FROM Material m WHERE m.materialName LIKE %?1%"
            + " OR m.quantity LIKE %?1%")
    Page<Material> findByFiltering(String materialName, String quantity, Pageable pageable);
}

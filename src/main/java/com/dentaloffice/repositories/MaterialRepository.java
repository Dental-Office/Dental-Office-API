package com.dentaloffice.repositories;

import com.dentaloffice.models.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface MaterialRepository extends JpaRepository<Material, UUID> {
//
//        @Query("SELECT new com.dentaloffice.models.Material(m.id, r, m.materialName, m.quantity) FROM Material m INNER JOIN m.enrolledRecords r WHERE m.materialName LIKE %?1%"
//            + " OR m.quantity LIKE %?2%")
    Page<Material> findByMaterialNameContainingOrQuantityContaining(String materialName, String quantity, Pageable pageable);
}

package com.dentaloffice.repositories;

import com.dentaloffice.models.DentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DentalServiceRepository extends JpaRepository<DentalService, UUID> {

    @Query("SELECT s FROM DentalService s WHERE s.serviceName LIKE %?1%")
    Page<DentalService> findByFiltering(String serviceName, Pageable pageable);
}

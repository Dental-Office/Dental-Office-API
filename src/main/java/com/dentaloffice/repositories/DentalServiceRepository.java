package com.dentaloffice.repositories;

import com.dentaloffice.models.DentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentalServiceRepository extends JpaRepository<DentalService, UUID> {

    Page<DentalService> findByServiceNameContainingIgnoreCase(String serviceName, Pageable pageable);
}
package com.dentaloffice.services;

import com.dentaloffice.models.DentalService;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DentalServiceService {

    DentalService save(DentalService service);

    Page<DentalService> findAll(String filter, Integer pageNo, Integer pageSize, String sortKey);

    void delete(UUID id);

    DentalService get(UUID id);

    boolean exists(UUID id);
}

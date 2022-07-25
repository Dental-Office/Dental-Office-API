package com.dentaloffice.services.impl;

import com.dentaloffice.models.Material;
import com.dentaloffice.repositories.DentalServiceRepository;
import com.dentaloffice.models.DentalService;
import com.dentaloffice.services.DentalServiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DentalDentalServiceServiceImpl implements DentalServiceService {

    private final DentalServiceRepository dentalServiceRepository;

    @Override
    public DentalService save(DentalService service) {
        return dentalServiceRepository.save(service);
    }

    @Override
    public Page<DentalService> findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        return dentalServiceRepository.findByFiltering(filter, pageable);
    }

    public boolean exists(UUID id){
        return dentalServiceRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        dentalServiceRepository.deleteById(id);
    }

    @Override
    public DentalService get(UUID id) {
        return dentalServiceRepository.getById(id);
    }
}

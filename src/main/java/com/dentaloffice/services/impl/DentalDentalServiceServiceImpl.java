package com.dentaloffice.services.impl;

import com.dentaloffice.repositories.DentalServiceRepository;
import com.dentaloffice.models.DentalService;
import com.dentaloffice.services.DentalServiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Page<DentalService> pagedResult = dentalServiceRepository.findByFiltering(filter, pageable);

        List<DentalService> persistedDentalServices = pagedResult.getContent();
        List<DentalService> dentalServices = persistedDentalServices.stream()
                .map(item -> new DentalService(item.getId(), item.getServiceName()))
                .collect(Collectors.toList());

        return new PageImpl<>(dentalServices, pagedResult.getPageable(), pagedResult.getTotalElements());
    }

    public boolean exists(UUID id){
        return dentalServiceRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        dentalServiceRepository.deleteById(id);
    }


    @Override
    public DentalService edit(DentalService editedDentalService) {
        return dentalServiceRepository.save(editedDentalService);
    }

    @Override
    public DentalService get(UUID id) {
        DentalService dentalServiceDb = dentalServiceRepository.getById(id);

        return new DentalService(dentalServiceDb.getId(), dentalServiceDb.getServiceName());
    }
}

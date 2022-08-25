package com.dentaloffice.controllers;

import com.dentaloffice.dto.DentalServiceRequestDTO;
import com.dentaloffice.dto.PageResponse;
import com.dentaloffice.models.DentalService;
import com.dentaloffice.services.DentalServiceService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(DentalServicesController.BASE_URL)
@AllArgsConstructor
public class DentalServicesController {

    public static final String BASE_URL = "/dentalService";
    private final DentalServiceService dentalServiceService;

    @GetMapping
    public PageResponse<DentalService> findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                               @RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(name = "sort", defaultValue = "serviceName", required = false) String sort) {
        Page<DentalService> pagedDentalServices = dentalServiceService.findAll(searchTerm, pageNo, pageSize, sort);

        PageResponse<DentalService> response = new PageResponse<>();
        response.setContent(pagedDentalServices.getContent());
        response.setTotalPages(pagedDentalServices.getTotalPages());

        return response;
    }

    @PostMapping
    public DentalService save(@Valid @RequestBody DentalServiceRequestDTO dentalService) {
        DentalService dentalServiceToBeSaved = new DentalService();
        dentalServiceToBeSaved.setServiceName(dentalService.getServiceName());

        return dentalServiceService.save(dentalServiceToBeSaved);
    }

    @GetMapping("{id}")
    @Transactional
    public DentalService get(@PathVariable UUID id) {
        return dentalServiceService.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        if (!dentalServiceService.exists(id)) {
            throwNotFoundException(id);
        }

        try {
            dentalServiceService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("{id}")
    public DentalService edit(@PathVariable UUID id, @Valid @RequestBody DentalServiceRequestDTO dentalServiceRequestDTO) {
        DentalService dentalServiceToBeSaved = new DentalService();
        dentalServiceToBeSaved.setId(id);
        dentalServiceToBeSaved.setServiceName(dentalServiceRequestDTO.getServiceName());

        if (!dentalServiceService.exists(id)) {
            throwNotFoundException(id);
        }

        return dentalServiceService.edit(dentalServiceToBeSaved);
    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

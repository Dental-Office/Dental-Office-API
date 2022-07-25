package com.dentaloffice.controllers;

import com.dentaloffice.dto.DentalServiceRequestDTO;
import com.dentaloffice.dto.DentalServiceResponseDTO;
import com.dentaloffice.models.DentalService;
import com.dentaloffice.services.DentalServiceService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public DentalServiceResponseDTO findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                            @RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(name = "sort", defaultValue = "serviceName", required = false) String sort) {
        Page<DentalService> pagedResult = dentalServiceService.findAll(searchTerm, pageNo, pageSize, sort);

        DentalServiceResponseDTO response = new DentalServiceResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @PostMapping
    public DentalService save(@Valid @RequestBody DentalServiceRequestDTO dentalService) {
        DentalService dentalServiceToBeSaved = new DentalService();
        dentalServiceToBeSaved.setServiceName(dentalService.getServiceName());

        return dentalServiceService.save(dentalServiceToBeSaved);
    }

    @GetMapping("{id}")
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

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

package com.dentaloffice.controllers;

import com.dentaloffice.dto.MaterialRequestDTO;
import com.dentaloffice.dto.MaterialResponseDTO;
import com.dentaloffice.models.Material;
import com.dentaloffice.services.MaterialService;
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
@RequestMapping(MaterialController.BASE_URL)
@AllArgsConstructor
public class MaterialController {

    public static final String BASE_URL = "/material";
    private final MaterialService materialService;

    @GetMapping
    public MaterialResponseDTO findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                       @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(name = "sort", defaultValue = "materialName", required = false) String sort) {
        Page<Material> pagedResult = materialService.findAll(searchTerm, pageNo, pageSize, sort);

        MaterialResponseDTO response = new MaterialResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @PostMapping
    public Material save(@Valid @RequestBody MaterialRequestDTO material) {
        Material materialToBeSaved = new Material();
        materialToBeSaved.setMaterialName(material.getMaterialName());
        materialToBeSaved.setQuantity(material.getQuantity());

        return materialService.save(materialToBeSaved);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        if (!materialService.exists(id)) {
            throwNotFoundException(id);
        }

        try {
            materialService.delete(id);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("{id}")
    public Material get(@PathVariable UUID id) {
        return materialService.get(id);
    }

    @PutMapping("{id}")
    public Material edit(@PathVariable UUID id, @Valid @RequestBody MaterialRequestDTO material) {
        Material materialToBeSaved = new Material();
        materialToBeSaved.setId(id);
        materialToBeSaved.setMaterialName(material.getMaterialName());
        materialToBeSaved.setQuantity(material.getQuantity());

        if (!materialService.exists(id)) {
            throwNotFoundException(id);
        }

        return materialService.edit(materialToBeSaved);
    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

package com.dentaloffice.controllers;

import com.dentaloffice.dto.MaterialRequestDTO;
import com.dentaloffice.dto.MaterialResponseDTO;
import com.dentaloffice.models.Material;
import com.dentaloffice.models.Record;
import com.dentaloffice.repositories.MaterialRepository;
import com.dentaloffice.repositories.RecordRepository;
import com.dentaloffice.services.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(MaterialController.BASE_URL)
@AllArgsConstructor
public class MaterialController {

    public static final String BASE_URL = "/material";
    private final MaterialService materialService;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    RecordRepository recordRepository;

    @GetMapping
    public MaterialResponseDTO findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                       @RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(name = "sort", defaultValue = "materialName", required = false) String sort) {
        Page<Material> fullMaterial = materialService.findAll(searchTerm, pageNo, pageSize, sort);

        MaterialResponseDTO response = new MaterialResponseDTO();

        List<Material> simplifiedMaterial = fullMaterial.getContent().stream().map(material -> {
            Set<Record> simplifiedEnrolledRecords = material.getEnrolledRecords()
                    .stream().map(record -> new Record(record.getId(), null, Collections.emptySet())).collect(Collectors.toSet());
            return new Material(material.getId(), simplifiedEnrolledRecords, material.getMaterialName(), material.getQuantity());
        }).collect(Collectors.toList());

        response.setContent(simplifiedMaterial);
        response.setTotalPages(fullMaterial.getTotalPages());

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

    @PutMapping("/{materialId}/records/{recordId}")
    Material enrolledRecordToMaterial(@PathVariable UUID materialId, @PathVariable UUID recordId){
        Material material = materialRepository.findById(materialId).get();
        Record record = recordRepository.findById(recordId).get();
        material.enrolledRecord(record);
        return materialRepository.save(material);
    }
}

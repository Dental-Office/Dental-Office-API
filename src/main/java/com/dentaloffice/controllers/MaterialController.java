package com.dentaloffice.controllers;

import com.dentaloffice.dto.MaterialRequestDTO;
import com.dentaloffice.dto.MaterialResponseDTO;
import com.dentaloffice.dto.PageResponse;
import com.dentaloffice.models.Material;
import com.dentaloffice.services.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(MaterialController.BASE_URL)
@AllArgsConstructor
public class MaterialController {

    public static final String BASE_URL = "/material";
    private final MaterialService materialService;

    @GetMapping
    public PageResponse<MaterialResponseDTO> findAll(@RequestParam(name = "searchTerm", required = false) String searchTerm,
                                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(name = "sort", defaultValue = "materialName", required = false) String sort) {
        Page<Material> pagedMaterials = materialService.findAll(searchTerm, pageNo, pageSize, sort);

        List<Material> materials = pagedMaterials.getContent();

        PageResponse<MaterialResponseDTO> response = new PageResponse<>();

        List<MaterialResponseDTO> convertedMaterials = materials.stream().map(item -> new MaterialResponseDTO(item.getId(), item.getMaterialName(), item.getQuantity())).collect(Collectors.toList());

        response.setContent(convertedMaterials);
        response.setTotalPages(pagedMaterials.getTotalPages());

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
    @Transactional
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
    ResponseEntity<Material> addRecord(@PathVariable UUID materialId, @PathVariable UUID recordId) {

        try {
            materialService.addRecord(materialId, recordId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

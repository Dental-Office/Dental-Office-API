package com.dentaloffice.dto;

import com.dentaloffice.models.Material;
import lombok.Data;

import java.util.List;

@Data
public class MaterialResponseDTO {

    private List<Material> content;

    private Integer totalPages;
}

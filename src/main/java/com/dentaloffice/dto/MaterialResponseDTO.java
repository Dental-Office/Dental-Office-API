package com.dentaloffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialResponseDTO {

    private UUID id;

    private String materialName;

    private String quantity;
}

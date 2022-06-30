package com.dentaloffice.dto;

import com.dentaloffice.models.DentalService;
import lombok.Data;

import java.util.List;

@Data
public class DentalServiceResponseDTO {

    private List<DentalService> content;

    private Integer totalPages;
}

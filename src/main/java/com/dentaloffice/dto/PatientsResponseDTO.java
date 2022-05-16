package com.dentaloffice.dto;

import com.dentaloffice.models.Patient;
import lombok.Data;

import java.util.List;

@Data
public class PatientsResponseDTO {
    private List<Patient> content;

    private Integer totalPages;
}
package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class RecordRequestDTO {

    @NotNull(message = "Patient id can not be null!")
    private String patientId;

}

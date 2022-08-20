package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RecordRequestDTO {

    @NotNull(message = "Patient id can not be null!")
    private String patientId;

}

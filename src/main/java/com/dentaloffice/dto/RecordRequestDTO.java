package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class RecordRequestDTO {

    @NotNull(message = "Patient id can not be null!")
    private String patientId;

    @NotNull(message = "Material id can not be null!")
    private List<UUID> materialIds = new ArrayList<>();

}

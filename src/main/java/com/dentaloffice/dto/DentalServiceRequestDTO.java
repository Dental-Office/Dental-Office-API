package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DentalServiceRequestDTO {

    @NotNull(message = "Service name can not be null!")
    @Size(min=2, max= 25, message = "Service name must be grater than 1 character and not grater than 25 characters!")
    @Pattern(regexp = "^[a-zA-Z]*", message = "Service name can not be a number!")
    private String serviceName;
}

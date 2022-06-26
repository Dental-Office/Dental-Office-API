package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MaterialRequestDTO {

    @NotNull(message = "First name can not be null!")
    @Size(min=2, max= 25, message = "First name must be grater than 1 character and not grater than 25 characters!")
    @Pattern(regexp = "^[a-zA-Z]*", message = "First name can not be a number!")
    private String materialName;

    @NotNull(message = "Quantity can not be null!")
    @Pattern(regexp = "[0-9]", message = "Quantity can not be a letter!")
    private String quantity;
}
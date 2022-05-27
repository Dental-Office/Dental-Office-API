package com.dentaloffice.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PatientRequestDTO {

    @NotNull(message = "First name can not be null!")
    @Size(min=2, max= 25, message = "First name must be grater than 1 character and not grater than 25 characters!")
    @Pattern(regexp = "^[a-zA-Z]*", message = "First name can not be a number!")
    private String firstName;

    @NotNull(message = "Last name can not be null!")
    @Size(min=2, max=30, message = "Last name must be grater than 1 character and not grater than 30 characters!")
    @Pattern(regexp = "^[a-zA-Z]*", message = "Last name can not be a number!")
    private String lastName;

    @NotNull(message = "Birth date can not be null!")
    private String birthDate;

    @NotBlank(message = "Phone number can not be null!")
    @Pattern(regexp = "[0-9]{11,15}", message = "Phone number can not be a character and must be grater than 10 or less than 15 numbers!")
    private String phoneNumber;
}

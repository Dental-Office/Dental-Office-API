package com.dentaloffice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AppointmentRequestDTO {

    @NotNull(message = "Patient id can not be null!")
    private UUID patientId;

    @NotNull(message = "Date of appointment can not be null!")
    private String date;

    @NotNull(message = "Time of appointment can not be null!")
    private String time;
}

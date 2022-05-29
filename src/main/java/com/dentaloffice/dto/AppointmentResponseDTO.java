package com.dentaloffice.dto;

import com.dentaloffice.models.Appointment;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentResponseDTO {

    private List<Appointment> content;
    private Integer totalPages;
}

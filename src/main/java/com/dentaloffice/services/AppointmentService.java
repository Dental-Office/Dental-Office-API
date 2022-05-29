package com.dentaloffice.services;

import com.dentaloffice.dto.AppointmentResponseDTO;
import com.dentaloffice.models.Appointment;

import java.util.UUID;

public interface AppointmentService {

    Appointment save(Appointment appointment);

    AppointmentResponseDTO findAll(String searchTerm, Integer pageNo, Integer pageSize, String sort);

    AppointmentResponseDTO findAll();

    boolean exists(UUID id);

    void delete(UUID id);

    Appointment get(UUID id);

    Appointment edit(Appointment appointmentToBeSaved);
}

package com.dentaloffice.services;

import com.dentaloffice.models.Appointment;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AppointmentService {

    Appointment save(Appointment appointment);

    Page<Appointment> findAll(String searchTerm, Integer pageNo, Integer pageSize, String sort);

    boolean exists(UUID id);

    void delete(UUID id);

    Appointment get(UUID id);

    Appointment edit(Appointment appointmentToBeSaved);
}

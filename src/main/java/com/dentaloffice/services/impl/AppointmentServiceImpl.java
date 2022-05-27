package com.dentaloffice.services.impl;

import com.dentaloffice.models.Appointment;
import com.dentaloffice.repositories.AppointmentRepository;
import com.dentaloffice.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}

package com.dentaloffice.controllers;

import com.dentaloffice.dto.AppointmentRequestDTO;
import com.dentaloffice.models.Appointment;
import com.dentaloffice.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AppointmentControler.BASE_URL)
@AllArgsConstructor
public class AppointmentControler {

    public static final String BASE_URL = "/appointment";
    private final AppointmentService appointmentService;

    @PostMapping
    public Appointment save(@Valid @RequestBody AppointmentRequestDTO appointment) {
        Appointment appointmentToBeSaved = new Appointment();
        appointmentToBeSaved.setPatientId(appointment.getPatientId());
        appointmentToBeSaved.setDate(appointment.getDate());
        appointmentToBeSaved.setTime(appointment.getTime());
        return appointmentService.save(appointmentToBeSaved);
    }
}

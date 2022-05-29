package com.dentaloffice.controllers;

import com.dentaloffice.dto.AppointmentRequestDTO;
import com.dentaloffice.dto.AppointmentResponseDTO;
import com.dentaloffice.models.Appointment;
import com.dentaloffice.models.Patient;
import com.dentaloffice.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

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
        Patient patient = new Patient();
        patient.setId(appointment.getPatientId());
        appointmentToBeSaved.setPatient(patient);
        appointmentToBeSaved.setDate(appointment.getDate());
        appointmentToBeSaved.setTime(appointment.getTime());
        return appointmentService.save(appointmentToBeSaved);
    }

    @GetMapping
    public AppointmentResponseDTO findAll(@RequestParam(name="searchTerm", required = false) String searchTerm,
                                          @RequestParam(defaultValue = "0") Integer pageNo,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(name = "sort", defaultValue = "date", required = false) String sort) {
        return appointmentService.findAll(searchTerm, pageNo, pageSize, sort);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        if (!appointmentService.exists(id)) {
            throwNotFoundException(id);
        }
        appointmentService.delete(id);
    }

    @GetMapping("{id}")
    public Appointment get(@PathVariable UUID id) {
        return appointmentService.get(id);
    }

//    @PutMapping("{id}")
//    public Appointment edit(@PathVariable UUID id, @Valid @RequestBody AppointmentRequestDTO appointment) {
//        Appointment appointmentToBeSaved = new Appointment();
//        Patient patient = new Patient();
//        patient.setId(appointment.getPatientId());
//        appointmentToBeSaved.setPatient(patient);
//        appointmentToBeSaved.setDate(appointment.getDate());
//        appointmentToBeSaved.setTime(appointment.getTime());
//
//        if (!appointmentService.exists(id)) {
//            throwNotFoundException(id);
//        }
//
//        return appointmentService.edit(appointmentToBeSaved);
//    }

    private void throwNotFoundException(UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data with id " + id + " exist");
    }
}

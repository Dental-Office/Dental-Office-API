package com.dentaloffice.services.impl;

import com.dentaloffice.models.Appointment;
import com.dentaloffice.repositories.AppointmentRepository;
import com.dentaloffice.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Page<Appointment> findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        Page<Appointment> pagedResult = appointmentRepository.findByFiltering(filter, filter, filter, pageable);

        List<Appointment> persistedAppointments = pagedResult.getContent();
        List<Appointment> appointments = persistedAppointments.stream()
                .map(item -> new Appointment(item.getId(), item.getPatient(), item.getDate(), item.getTime()))
                .collect(Collectors.toList());

        return new PageImpl<>(appointments, pagedResult.getPageable(), pagedResult.getTotalElements());
    }

    public boolean exists(UUID id){
        return appointmentRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        appointmentRepository.deleteById(id);
    }


    @Override
    public Appointment get(UUID id) {
        Appointment appointmentDb = appointmentRepository.getById(id);

        return new Appointment(appointmentDb.getId(), appointmentDb.getPatient(), appointmentDb.getDate(), appointmentDb.getTime());
    }

    @Override
    public Appointment edit(Appointment editedAppointment) {
        return appointmentRepository.save(editedAppointment);
    }

}

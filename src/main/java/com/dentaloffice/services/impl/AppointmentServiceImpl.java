package com.dentaloffice.services.impl;

import com.dentaloffice.models.Appointment;
import com.dentaloffice.models.Patient;
import com.dentaloffice.repositories.AppointmentRepository;
import com.dentaloffice.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        return appointmentRepository.findByFiltering(filter, filter, filter,  pageable);
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
        return appointmentRepository.getById(id);
    }

    @Override
    public Appointment edit(Appointment editedAppointment) {
        return appointmentRepository.save(editedAppointment);
    }

}

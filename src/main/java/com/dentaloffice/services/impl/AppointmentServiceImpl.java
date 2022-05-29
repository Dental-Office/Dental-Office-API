package com.dentaloffice.services.impl;

import com.dentaloffice.dto.AppointmentResponseDTO;
import com.dentaloffice.models.Appointment;
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
    public AppointmentResponseDTO findAll(String filter, Integer pageNo, Integer pageSize, String sort) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());

        Page<Appointment> pagedResult = appointmentRepository.findByFiltering(filter, filter, filter,  pageable);

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setContent(pagedResult.getContent());
        response.setTotalPages(pagedResult.getTotalPages());

        return response;
    }

    @Override
    public AppointmentResponseDTO findAll(){
        AppointmentResponseDTO response = new AppointmentResponseDTO();

        response.setContent(appointmentRepository.findAll());

        return response;
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
    public Appointment edit(Appointment appointmentToBeSaved) {
        return null;
    }
}

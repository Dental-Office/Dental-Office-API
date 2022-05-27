package com.dentaloffice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;

    private String date;

    private String time;
}

package com.dentaloffice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String phoneNumber;
}

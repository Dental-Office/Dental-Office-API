package com.dentaloffice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties(value= {"records"})
//Anotation used because I do not wont to have Record property in Patient but I still wont have a relation.
public class Patient {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String phoneNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
//    @Column(nullable = true)
//    @JsonManagedReference

    private Set<Record> records = new HashSet<>();
}

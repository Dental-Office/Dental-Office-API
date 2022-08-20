package com.dentaloffice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Record {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Patient patient;

    @ManyToMany(mappedBy = "enrolledRecords", fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
    private Set<Material> materials = new HashSet<>();
}

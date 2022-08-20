package com.dentaloffice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="record_enrolled",
            joinColumns = @JoinColumn(name = "material_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Record> enrolledRecords = new HashSet<>();

    private String materialName;

    private String quantity;

//     Move to other place
    public void enrolledRecord(Record record) {
        enrolledRecords.add(record);
    }
}

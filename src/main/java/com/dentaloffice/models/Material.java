package com.dentaloffice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value= {"enrolledRecords"})
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

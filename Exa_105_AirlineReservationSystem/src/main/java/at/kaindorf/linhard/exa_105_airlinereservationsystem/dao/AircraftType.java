package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "aircraft_type")
public class AircraftType {

    @Id
    @GeneratedValue
    @Column(name = "aircraft_type_id")
    private Long id;

    @Column(name = "type_name", length = 50)
    private String typeName;

    @Column
    private int seats;

    @OneToMany(mappedBy = "aircraftType")
    private List<Aircraft> aircraft;
}

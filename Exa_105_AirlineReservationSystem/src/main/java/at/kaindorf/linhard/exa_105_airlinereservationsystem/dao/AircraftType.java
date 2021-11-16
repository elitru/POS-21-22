package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "aircraft_type")
public class AircraftType {

    @Id
    @GeneratedValue
    @Column(name = "aircraft_type_id")
    private Long id;

    @Column(name = "type_name", length = 70)
    @NonNull
    private String typeName;

    @Column
    @NonNull
    private int seats;

    public AircraftType(String[] line) {
        this.typeName = String.format("%s [%s]", line[1], line[2], Integer.parseInt(line[8]));
    }
}

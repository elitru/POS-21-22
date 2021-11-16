package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "airline")
@IdClass(AirlinePK.class)
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Airline.countAircraftTypesForAirline",
                query = "SELECT DISTINCT COUNT(aircraft_type) FROM Airline airline JOIN airline.aircraft aircraft JOIN aircraft.aircraftType aircraft_type WHERE airline.airlineName = :name"
        )
})
public class Airline {
    @Id
    @GeneratedValue
    @Column(name = "airline_id")
    @NonNull
    private Long id;

    @Id
    @Column(name = "airline_name", length = 120)
    @NonNull
    private String airlineName;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aircraft> aircraft = new ArrayList<>();

    @OneToMany(mappedBy = "airline", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flight> flights = new ArrayList<>();

    public Airline(String[] line) {
        this.id = Long.parseLong(line[0]);
        this.airlineName = line[1];
    }
}

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
@Table(name = "airline")
@IdClass(AirlinePK.class)
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
    private Long id;

    @Id
    @Column(name = "airline_name", length = 40)
    private String airlineName;

    @OneToMany(mappedBy = "airline")
    private List<Aircraft> aircraft;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
}

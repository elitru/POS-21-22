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
@Table(name = "aircraft")
@NamedQueries({
        @NamedQuery(
                name = "Aircraft.getFlightsFromAirport",
                query = "SELECT flight FROM Aircraft aircraft JOIN aircraft.flights flight JOIN flight.departureAirport da WHERE da.name = :airport"
        )
})
public class Aircraft {
    @Id
    @GeneratedValue
    @Column(name = "aircraft_id")
    private Long id;

    @ManyToMany(mappedBy = "aircraft")
    private List<Airport> airports;

    @ManyToOne
    @JoinColumn(name = "aircraft_type_id")
    private AircraftType aircraftType;

    @ManyToOne
    private Airline airline;

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights;
}

package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
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

    @ManyToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<Airport> airports = new ArrayList<>();

    @ManyToOne
    @NonNull
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_type_id")
    @NonNull
    private AircraftType aircraftType;

    @OneToMany(mappedBy = "aircraft", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();
}

package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airport")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Airport.getArrivingFlightsForAirline",
                query = "SELECT flight FROM Airport airport JOIN airport.arrivalFlights flight JOIN flight.airline airline WHERE airline = :airline"
        ),
        @NamedQuery(
                name = "Airport.getDepartureFlightsForAirline",
                query = "SELECT flight FROM Airport airport JOIN airport.departureFlights flight JOIN flight.airline airline WHERE airline = :airline"
        ),
        @NamedQuery(
                name = "Airport.countAircraftVisit",
                query = "SELECT COUNT(aircraft) FROM Airport airport JOIN airport.aircraft aircraft WHERE aircraft.id = :id"
        ),
        @NamedQuery(
                name = "Airport.countAirlineDeparture",
                query = "SELECT COUNT(flight) FROM Airport airport JOIN airport.departureFlights flight WHERE flight.airline.airlineName = :airline"
        )
})
public class Airport {
    @Id
    @GeneratedValue
    @Column(name = "airport_id")
    private Long id;

    @Column(name = "country", length = 80)
    @NonNull
    private String country;

    @Column(name = "city", length = 80)
    @NonNull
    private String city;

    @Column(name = "name", length = 80)
    @NonNull
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "aircraft_airport", joinColumns = {
            @JoinColumn(name = "airport_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "aircraft_id")
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Aircraft> aircraft = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flight> arrivalFlights = new ArrayList<>();

    @OneToMany(mappedBy = "departureAirport", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Flight> departureFlights = new ArrayList<>();

    public Airport(String[] line) {
        this.country = line[8];
        this.city = line[10];
        this.name = line[3];
    }
}
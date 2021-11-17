package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Flight.countFlightsForAircraftType",
                query = "SELECT COUNT(aircraft_type) FROM Flight flight JOIN flight.aircraft aircraft JOIN aircraft.aircraftType aircraft_type WHERE aircraft_type.id = :id"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfArrivalFlightsForAirport",
                query = "SELECT COUNT(f) FROM Flight f WHERE f.arrivalAirport.name = :airportName"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfDepartureFlightsForAirport",
                query = "SELECT COUNT(f) FROM Flight f WHERE f.departureAirport.name = :airportName"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfArrivalFlightsFromToDateForAirport",
                query = "SELECT COUNT(f) FROM Flight f WHERE f.arrivalAirport.name = :name AND f.arrivalTime >= :from AND f.arrivalTime <= :to"
        )
})
public class Flight {
    @Id
    @GeneratedValue
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "departure_time")
    @NonNull
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    @NonNull
    private LocalTime arrivalTime;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Airport departureAirport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Airport arrivalAirport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Aircraft aircraft;

    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Airline airline;
}

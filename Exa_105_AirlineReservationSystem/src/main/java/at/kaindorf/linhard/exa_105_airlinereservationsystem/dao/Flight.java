package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedQueries({
        @NamedQuery(
                name = "Flight.countFlightsForAircraftType",
                query = "SELECT COUNT(aircraft_type) FROM Flight flight JOIN flight.aircraft aircraft JOIN aircraft.aircraftType aircraft_type"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfArrivalFlightsForAirport",
                query = "SELECT COUNT(f) FROM Flight f where f.arrivalAirport.name = :airportName"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfDepartureFlightsForAirport",
                query = "SELECT COUNT(f) FROM Flight f where f.departureAirport.name = :airportName"
        ),
        @NamedQuery(
                name = "Flight.getAmountOfFlightsInTheLastNDaysForAirport",
                query = "SELECT COUNT(f) FROM Flight f WHERE f.departureTime BETWEEN CURRENT_DATE - (:n) AND CURRENT_DATE AND f.arrivalAirport.name = :name"
        )
})
public class Flight {

    @Id
    @GeneratedValue
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;


    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    private Airline airline;
}

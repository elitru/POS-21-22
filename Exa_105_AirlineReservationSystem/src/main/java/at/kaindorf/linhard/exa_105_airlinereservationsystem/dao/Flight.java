package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "flight")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

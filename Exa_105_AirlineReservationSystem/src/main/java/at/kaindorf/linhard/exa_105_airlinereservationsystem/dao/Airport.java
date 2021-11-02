package at.kaindorf.linhard.exa_105_airlinereservationsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "airport")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Airport {
    @Id
    @GeneratedValue
    @Column(name = "airport_id")
    private Long id;

    @Column(name = "country", length = 60)
    private String country;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "name", length = 60)
    private String name;

    @ManyToMany
    @JoinTable(name = "aircraft_airport", joinColumns = {
            @JoinColumn(name = "airport_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "aircraft_id")
    })
    private List<Aircraft> aircraft = new ArrayList<>();


    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalAirports;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureAirports;
}

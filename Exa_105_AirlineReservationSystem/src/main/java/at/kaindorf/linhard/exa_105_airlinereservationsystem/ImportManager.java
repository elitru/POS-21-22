package at.kaindorf.linhard.exa_105_airlinereservationsystem;

import at.kaindorf.linhard.exa_105_airlinereservationsystem.dao.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Persistence;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class ImportManager {
    private static final Random RND = new Random();

    private EntityManagerFactory ef;
    private EntityManager em;

    public void setup() throws FileNotFoundException {
        List<Airport> airports = new ArrayList<>(IOHandler.loadAirports());
        List<Airline> airlines = new ArrayList<>(IOHandler.loadAirlines());
        List<AircraftType> aircraftTypes = new ArrayList<>(IOHandler.loadAircraftTypes());

        System.out.println("Loading predefined data...");

        airports.forEach(em::persist);
        airlines.forEach(em::persist);
        aircraftTypes.forEach(em::persist);

        this.save();

        this.insertTestData(airports, airlines, aircraftTypes);

        this.save();
    }

    private void insertTestData(List<Airport> airports, List<Airline> airlines, List<AircraftType> aircraftTypes) {
        List<Aircraft> aircraft = this.generateRandomAircraft(airlines, aircraftTypes, airports, 200);
        aircraft.forEach(em::persist);

        List<Flight> flights = this.generateRandomFlights(airlines, airports, aircraft, 1000);
        flights.forEach(em::persist);
    }

    private List<Flight> generateRandomFlights(List<Airline> airlines, List<Airport> airports, List<Aircraft> aircraft, int amount) {
        List<Flight> result = new ArrayList<>();
        List<Integer> randomAirlines = getRandomIndices(amount, airlines.size());
        List<Integer> randomAirports = getRandomIndices(amount * 2, airports.size());
        List<Integer> randomAircraft = getRandomIndices(amount, aircraft.size());

        for (int i = 0; i < amount; i++) {
            Airline chosenAirline = airlines.get(randomAirlines.get(i));
            Airport chosenArrivalAirport = airports.get(randomAirports.get(i));
            Airport chosenDepartureAirport = airports.get(randomAirports.get(i + amount - 1));
            Aircraft chosenAircraft = aircraft.get(randomAircraft.get(i));

            Flight flight = new Flight();

            flight.setArrivalAirport(chosenArrivalAirport);
            chosenArrivalAirport.getArrivalFlights().add(flight);

            flight.setDepartureAirport(chosenDepartureAirport);
            chosenDepartureAirport.getDepartureFlights().add(flight);

            flight.setAirline(chosenAirline);
            chosenAirline.getFlights().add(flight);

            flight.setAircraft(chosenAircraft);
            chosenAircraft.getFlights().add(flight);

            flight.setArrivalTime(getRandomTime());
            flight.setDepartureTime(getRandomTime());

            result.add(flight);
        }
        return result;

    }

    private static LocalTime getRandomTime() {
        int min = LocalTime.MIN.toSecondOfDay();
        int max = LocalTime.MAX.toSecondOfDay();
        int randomTime = ThreadLocalRandom.current().nextInt(min, max);

        return LocalTime.ofSecondOfDay(randomTime);
    }

    private List<Aircraft> generateRandomAircraft(List<Airline> airlines, List<AircraftType> types, List<Airport> airports, int amount) {
        List<Aircraft> result = new ArrayList<>();
        Iterator<Integer> randomAirlines = getRandomIndices(amount, airlines.size()).iterator();
        Iterator<Integer> randomAirports = getRandomIndices(amount, airports.size()).iterator();
        Iterator<Integer> randomAircraftTypes = getRandomIndices(amount, types.size()).iterator();

        for (int i = 0; i < amount; i++) {
            Airline airline = airlines.get(randomAirlines.next());
            Airport airport = airports.get(randomAirports.next());
            AircraftType aircraftType = types.get(randomAircraftTypes.next());

            Aircraft aircraft = new Aircraft();

            aircraft.getAirports().add(airport);
            airport.getAircraft().add(aircraft);

            aircraft.setAirline(airline);
            airline.getAircraft().add(aircraft);

            aircraft.setAircraftType(aircraftType);

            result.add(aircraft);
        }
        return result;
    }

    private static List<Integer> getRandomIndices(int amount, int to) {
        List<Integer> indices = new ArrayList<>();

        while (indices.size() != amount) {
            indices.add(RND.nextInt(to));
        }

        return indices;
    }

    private void save() {
        this.em.getTransaction().begin();
        this.em.getTransaction().commit();
    }
}

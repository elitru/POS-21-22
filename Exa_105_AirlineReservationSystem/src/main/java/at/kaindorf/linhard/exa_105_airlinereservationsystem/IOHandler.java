package at.kaindorf.linhard.exa_105_airlinereservationsystem;

import at.kaindorf.linhard.exa_105_airlinereservationsystem.dao.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class IOHandler {
    private static File getFileFromResources(String filename) {
        return Paths.get(System.getProperty("user.dir"), "src", "main", "resources", filename).toFile();
    }

    public static Collection<AircraftType> loadAircraftTypes() throws FileNotFoundException {
        File f = getFileFromResources("aircrafttypes.csv");
        return new BufferedReader(new FileReader(f))
                .lines()
                .skip(1)
                .map(l -> l.split(","))
                .map(IOHandler::trimData)
                .map(AircraftType::new)
                .collect(Collectors.toSet());
    }

    public static Collection<Airport> loadAirports() throws FileNotFoundException {
        File f = getFileFromResources("airports.csv");
        return new BufferedReader(new FileReader(f))
                .lines()
                .skip(1)
                .map(l -> l.split(","))
                .map(IOHandler::trimData)
                .map(Airport::new)
                .collect(Collectors.toSet());
    }

    public static Collection<Airline> loadAirlines() throws FileNotFoundException {
        File f = getFileFromResources("airlines.csv");
        return new BufferedReader(new FileReader(f))
                .lines()
                .skip(1)
                .map(l -> l.split(","))
                .map(IOHandler::trimData)
                .map(Airline::new)
                .collect(Collectors.toSet());
    }

    private static String[] trimData(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].trim();
        }
        return input;
    }
}

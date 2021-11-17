package at.kaindorf.linhard.exa_105_airlinereservationsystem;

import at.kaindorf.linhard.exa_105_airlinereservationsystem.dao.Flight;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_AIRSYS");
        EntityManager em = emf.createEntityManager();

        ImportManager manager = new ImportManager(emf, em);
        manager.setup();

        run(em);

        em.close();
        emf.close();
    }

    private static void run(EntityManager em) {
        LOOP:
        while(true) {
            String option = getSelectedQuery();

            switch (option) {
                case "1": {
                    System.out.println("Airport: ");
                    TypedQuery<Flight> query = em.createNamedQuery("Aircraft.getFlightsFromAirport", Flight.class);
                    query.setParameter("airport", SCANNER.nextLine().trim());
                    query.getResultList().forEach(System.out::println);
                    break;
                }
                case "2": {
                    System.out.println("Airline: ");
                    TypedQuery<Long> query = em.createNamedQuery("Airline.countAircraftTypesForAirline", Long.class);
                    query.setParameter("name", SCANNER.nextLine().trim());
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "3": {
                    System.out.println("Airline: ");
                    TypedQuery<Flight> query = em.createNamedQuery("Airport.getArrivingFlightsForAirline", Flight.class);
                    query.setParameter("airline", SCANNER.nextLine().trim());
                    query.getResultList().forEach(System.out::println);
                    break;
                }
                case "4": {
                    System.out.println("Airline: ");
                    TypedQuery<Flight> query = em.createNamedQuery("Airport.getDepartureFlightsForAirline", Flight.class);
                    query.setParameter("airline", SCANNER.nextLine().trim());
                    query.getResultList().forEach(System.out::println);
                    break;
                }
                case "5": {
                    System.out.println("Aircraft ID: ");
                    TypedQuery<Long> query = em.createNamedQuery("Airport.countAircraftVisit", Long.class);
                    query.setParameter("id", Long.parseLong(SCANNER.nextLine().trim()));
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "6": {
                    System.out.println("Airline: ");
                    TypedQuery<Long> query = em.createNamedQuery("Airport.countAirlineDeparture", Long.class);
                    query.setParameter("airline", SCANNER.nextLine().trim());
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "7": {
                    System.out.println("AircraftType ID: ");
                    TypedQuery<Long> query = em.createNamedQuery("Flight.countFlightsForAircraftType", Long.class);
                    query.setParameter("id", Long.parseLong(SCANNER.nextLine().trim()));
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "8": {
                    System.out.println("Airport: ");
                    TypedQuery<Long> query = em.createNamedQuery("Flight.getAmountOfArrivalFlightsForAirport", Long.class);
                    query.setParameter("airportName", SCANNER.nextLine().trim());
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "9": {
                    System.out.println("Airport: ");
                    TypedQuery<Long> query = em.createNamedQuery("Flight.getAmountOfDepartureFlightsForAirport", Long.class);
                    query.setParameter("airportName", SCANNER.nextLine().trim());
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "10": {
                    System.out.println("Airport: ");
                    TypedQuery<Long> query = em.createNamedQuery("Flight.getAmountOfArrivalFlightsFromToDateForAirport", Long.class);
                    query.setParameter("name", SCANNER.nextLine().trim());
                    query.setParameter("from", LocalTime.now().minusHours(5));
                    query.setParameter("to", LocalTime.now().plusHours(1));
                    System.out.println("Result: " + query.getSingleResult().toString());
                    break;
                }
                case "0":
                    break LOOP;
                default:
                    continue;
            }
        }
    }

    private static String getSelectedQuery() {
        System.out.println("To start, chose one of the following queries:");
        System.out.println("1) Aircraft.getFlightsFromAirport");
        System.out.println("2) Airline.countAircraftTypesForAirline");
        System.out.println("3) Airport.getArrivingFlightsForAirline");
        System.out.println("4) Airport.getDepartureFlightsForAirline");
        System.out.println("5) Airport.countAircraftVisit");
        System.out.println("6) Airport.countAirlineDeparture");
        System.out.println("7) Flight.countFlightsForAircraftType");
        System.out.println("8) Flight.getAmountOfArrivalFlightsForAirport");
        System.out.println("9) Flight.getAmountOfDepartureFlightsForAirport");
        System.out.println("10) Flight.getAmountOfFlightsFromToDateForAirport");
        System.out.println("0) Exit");
        System.out.print("Your choice: ");
        return SCANNER.nextLine().trim();
    }
}

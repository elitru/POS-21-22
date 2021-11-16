package at.kaindorf.linhard.exa_105_airlinereservationsystem;

import at.kaindorf.linhard.exa_105_airlinereservationsystem.dao.Flight;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
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
                case "1":
                    System.out.println("Airport: ");
                    TypedQuery<Flight> query = em.createNamedQuery("Aircraft.getFlightsFromAirport", Flight.class);
                    query.setParameter("airport", SCANNER.nextLine().trim());
                    query.getResultList().forEach(System.out::println);
                    break;
                case "0":
                    break LOOP;
            }
        }
    }

    private static String getSelectedQuery() {
        System.out.println("To start, chose one of the following queries:");
        System.out.println("1) Aircraft.getFlightsFromAirport");
        System.out.println("2) ");
        System.out.println("3) ");
        System.out.println("4) ");
        System.out.println("5) ");
        System.out.println("6) ");
        System.out.println("8) ");
        System.out.println("8) ");
        System.out.println("9) ");
        System.out.println("10) ");
        System.out.println("0) Exit");
        System.out.print("Your choice: ");
        return SCANNER.nextLine().trim();
    }
}

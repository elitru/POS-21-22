package at.eliastrummer.Exa_103;

import at.eliastrummer.Exa_103.dao.Country;
import at.eliastrummer.Exa_103.dao.Customer;
import at.eliastrummer.Exa_103.dto.ParseResult;
import at.eliastrummer.Exa_103.io.IOHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Customer");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            int action = getSelectedOption(scanner);
            if (action == 0) break;

            switch (action) {
                case 1:
                    importData("json", em);
                    break;
                case 2:
                    importData("xml", em);
                    break;
                case 3:
                    List<Double> years = em.createNamedQuery("Customer.findYears", Double.class).getResultList();
                    years.stream().forEach(y -> System.out.format("%.0f\n", y));
                    break;
                case 4:
                    System.out.println("Enter the country code to search for: ");
                    String countryCode = scanner.next();
                    TypedQuery<Customer> query = em.createNamedQuery("Customer.findFromCountry", Customer.class);
                    query.setParameter("country", countryCode.toUpperCase());
                    List<Customer> customers = query.getResultList();
                    customers.stream().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Enter the country name to search for: ");
                    String countryName = scanner.next();
                    TypedQuery<Country> countryQuery = em.createNamedQuery("Country.findByName", Country.class);
                    countryQuery.setParameter("country", countryName.trim().toLowerCase());
                    try {
                        System.out.println(countryQuery.getSingleResult().toString());
                    }catch (Exception ex) {
                        System.out.println("No entries found...");
                    }
                    break;
                case 6:
                    List<Country> countries = em.createNamedQuery("Country.findAll", Country.class).getResultList();
                    countries.stream().forEach(System.out::println);
                    break;
            }
        }

        em.close();
        emf.close();
    }

    private static void importData(String type, EntityManager em) throws IOException {
        ParseResult parseResult = null;

        if (type.equals("json")) {
            parseResult = IOHandler.getJsonData();
        }else{
            parseResult = IOHandler.getXmlData();
        }

        em.getTransaction().begin();
        parseResult.getCustomers().forEach(c -> em.persist(c));
        em.getTransaction().commit();

        long amountCustomers = em.createNamedQuery("Customer.countAll", Long.class).getSingleResult();
        long amountAddresses = em.createNamedQuery("Address.countAll", Long.class).getSingleResult();
        long amountCountries = em.createNamedQuery("Country.countAll", Long.class).getSingleResult();

        System.out.println("Countries imported: " + amountCountries);
        System.out.println("Addresses imported: " + amountAddresses);
        System.out.println("Customers imported: " + amountCustomers);
    }

    private static int getSelectedOption(Scanner scanner) {
        System.out.printf("Choose an action");
        System.out.println("(0) Exit");
        System.out.println("(1) Import JSON");
        System.out.println("(2) Import XML");
        System.out.println("(3) Query Customer -> findYears");
        System.out.println("(4) Query Customer -> findFromCountry");
        System.out.println("(5) Query Country -> findByName");
        System.out.println("(6) Query Country -> findAll");
        System.out.println("Action: ");
        String actionRaw = scanner.next();
        return Integer.parseInt(actionRaw);
    }
}

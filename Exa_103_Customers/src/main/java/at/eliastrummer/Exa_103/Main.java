package at.eliastrummer.Exa_103;

import at.eliastrummer.Exa_103.dto.ParseResult;
import at.eliastrummer.Exa_103.io.IOHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Customer");
        EntityManager em = emf.createEntityManager();

        ParseResult result = IOHandler.getJsonData();
        result.getCustomers().forEach(c -> System.out.println(c));

        em.getTransaction().begin();
        result.getCustomers().forEach(c -> em.persist(c));
        em.getTransaction().commit();


        em.close();
        emf.close();
    }
}

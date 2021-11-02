package at.kaindorf.linhard.exa_105_airlinereservationsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_AIRSYS");
        EntityManager manager = emf.createEntityManager();

        manager.close();
        emf.close();
    }
}

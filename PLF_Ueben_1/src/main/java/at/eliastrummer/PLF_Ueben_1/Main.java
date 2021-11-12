package at.eliastrummer.PLF_Ueben_1;

import at.eliastrummer.PLF_Ueben_1.pojo.Customer;
import at.eliastrummer.PLF_Ueben_1.pojo.Order;
import at.eliastrummer.PLF_Ueben_1.pojo.Product;
import at.eliastrummer.PLF_Ueben_1.pojo.ProductsWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Test");
        EntityManager em = emf.createEntityManager();

        List<Customer> customers = parseCustomers();
        List<Product> products = parseProducts();

        Order order = new Order(1, 1, new ArrayList<Product>(){{
            add(products.get(0));
            add(products.get(2));
        }}, customers.get(1));

        products.get(0).getOrders().add(order);
        products.get(2).getOrders().add(order);
        customers.get(1).getOrders().add(order);

        Order order2 = new Order(2, 2, new ArrayList<Product>(){{
            add(products.get(2));
        }}, customers.get(0));

        products.get(2).getOrders().add(order2);
        customers.get(0).getOrders().add(order2);

        em.getTransaction().begin();

        customers.forEach(em::persist);
        products.forEach(em::persist);

        em.getTransaction().commit();

        Customer john = em.find(Customer.class, (long)1);
        System.out.println(john);

        TypedQuery<Integer> query = em.createNamedQuery("count", Integer.class);
        System.out.println(query.getSingleResult());

        TypedQuery<Customer> query2 = em.createNamedQuery("customer", Customer.class);
        System.out.println(query2.getResultList().size());

        em.createQuery("SELECT c FROM Customer c", Customer.class);

        em.close();
        emf.close();
    }

    private static List<Customer> parseCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.json").toFile();
        return mapper.readValue(file, new TypeReference<List<Customer>>() { });
    }

    private static List<Product> parseProducts() throws IOException {
        File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "products.xml").toFile();
        return JAXB.unmarshal(file, ProductsWrapper.class).getProducts();
    }
}

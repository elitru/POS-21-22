package at.eliastrummer.repositories;

import at.eliastrummer.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE LOWER(c.lastname) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY c.lastname, c.firstname")
    List<Customer> findCustomersByLastnameOrdered(String name);

    @Query("SELECT COUNT(c) * 1.0 / (SELECT COUNT(c2) FROM Customer c2) FROM Customer c WHERE c.gender = 'm'")
    double getPercentageOfMaleCustomers();

    @Query("SELECT COUNT(c) * 1.0 / (SELECT COUNT(c2) FROM Customer c2) FROM Customer c WHERE c.gender = 'w'")
    double getPercentageOfFemaleCustomers();

    @Query("SELECT c FROM Customer c WHERE c.address.city = :city")
    List<Customer> findCustomersByCity(String city);
}
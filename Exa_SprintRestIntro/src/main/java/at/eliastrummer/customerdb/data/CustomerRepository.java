package at.eliastrummer.customerdb.data;

import at.eliastrummer.customerdb.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
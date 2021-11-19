package at.kaindorf.trummer.springburgerapp.repositories;

import at.kaindorf.trummer.springburgerapp.pojos.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
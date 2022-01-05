package at.eliastrummer.repositories;

import at.eliastrummer.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

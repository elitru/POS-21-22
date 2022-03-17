package at.eliastrummer.plf2.repositories;

import at.eliastrummer.plf2.pojos.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
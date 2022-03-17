package at.eliastrummer.plf2.repositories;

import at.eliastrummer.plf2.pojos.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Hobby findByNameIgnoreCase(String name);
}
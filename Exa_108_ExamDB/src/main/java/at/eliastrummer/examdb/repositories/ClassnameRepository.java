package at.eliastrummer.examdb.repositories;

import at.eliastrummer.examdb.pojos.Classname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassnameRepository extends JpaRepository<Classname, Long> {
}
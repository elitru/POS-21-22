package at.eliastrummer.plf2.repositories;

import at.eliastrummer.plf2.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
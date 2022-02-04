package at.eliastrummer.examdb.repositories;

import at.eliastrummer.examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
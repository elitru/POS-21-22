package at.eliastrummer.plf2.repositories;

import at.eliastrummer.plf2.pojos.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
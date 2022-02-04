package at.eliastrummer.examdb.repositories;

import at.eliastrummer.examdb.pojos.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.classname.classId = :classId ORDER BY s.lastname, s.firstname")
    List<Student> getStudentsForClass(long classId, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.classname.classId = :classId ORDER BY s.lastname, s.firstname")
    List<Student> getStudentsForClass(long classId);
}
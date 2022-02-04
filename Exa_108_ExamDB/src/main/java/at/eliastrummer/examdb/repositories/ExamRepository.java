package at.eliastrummer.examdb.repositories;

import at.eliastrummer.examdb.pojos.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("SELECT e FROM Exam e WHERE e.student.studentId = :studentId AND e.examId = :examId")
    Exam getExamForStudent(long studentId, long examId);
}
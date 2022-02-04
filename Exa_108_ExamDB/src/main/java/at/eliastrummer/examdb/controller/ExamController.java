package at.eliastrummer.examdb.controller;

import at.eliastrummer.examdb.models.CreateExamRequest;
import at.eliastrummer.examdb.models.UpdateExamRequest;
import at.eliastrummer.examdb.pojos.Exam;
import at.eliastrummer.examdb.pojos.Student;
import at.eliastrummer.examdb.pojos.Subject;
import at.eliastrummer.examdb.repositories.ExamRepository;
import at.eliastrummer.examdb.repositories.StudentRepository;
import at.eliastrummer.examdb.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Exam>> getExamsForStudent(@PathVariable("studentId") long studentId) {
        return ResponseEntity.of(studentRepository.findById(studentId).map(Student::getExams));
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<Exam> addExamForStudent(@PathVariable("studentId") long studentId, @RequestBody CreateExamRequest request) {
        var student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var exam = request.toExam(student.get(), subject.get());
        exam = examRepository.save(exam);
        return ResponseEntity.ok(exam);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<Exam> updateExamForStudent(@PathVariable("studentId") long studentId, @RequestBody UpdateExamRequest request) {
        var exam = examRepository.findById(request.getId());
        var student = studentRepository.findById(studentId);
        if (student.isEmpty() || exam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var subject = request.getSubjectId() != null ?
                subjectRepository.findById(request.getSubjectId()) :
                Optional.of(exam.get().getSubject());
        if (subject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var examToBeUpdated = exam.get();
        examToBeUpdated.setDuration(request.getDuration() == null ? exam.get().getDuration() : request.getDuration());
        examToBeUpdated.setDateOfExam(request.getDateOfExam() == null ? exam.get().getDateOfExam() : request.getDateOfExam());
        examToBeUpdated.setSubject(subject.get());

        examRepository.save(examToBeUpdated);
        return ResponseEntity.ok(examToBeUpdated);
    }

    @DeleteMapping("/{studentId}/{examId}")
    public ResponseEntity<Void> deleteExamForStudent(@PathVariable("studentId") long studentId, @PathVariable("examId") long examId) {
        var exam = examRepository.getExamForStudent(studentId, examId);
        if (exam == null) {
            return ResponseEntity.notFound().build();
        }

        examRepository.delete(exam);
        return ResponseEntity.ok().build();
    }
}
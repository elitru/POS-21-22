package at.eliastrummer.examdb.controller;

import at.eliastrummer.examdb.pojos.Student;
import at.eliastrummer.examdb.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private static final int PAGE_SIZE = 10;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getStudentsForClass(@RequestParam("classId") long classId, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return studentRepository.getStudentsForClass(classId, pageable);
    }

    @GetMapping("/pages")
    public int getPages(@RequestParam("classId") long classId) {
        return (int) Math.ceil(studentRepository.getStudentsForClass(classId).size() * 1.0 / PAGE_SIZE);
    }
}
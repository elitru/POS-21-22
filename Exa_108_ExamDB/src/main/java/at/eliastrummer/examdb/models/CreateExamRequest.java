package at.eliastrummer.examdb.models;

import at.eliastrummer.examdb.pojos.Exam;
import at.eliastrummer.examdb.pojos.Student;
import at.eliastrummer.examdb.pojos.Subject;
import at.eliastrummer.examdb.utils.DateDeserializer;
import at.eliastrummer.examdb.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateExamRequest {
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate dateOfExam;
    private Integer duration;
    private Long subjectId;

    public Exam toExam(Student student, Subject subject) {
        var exam = new Exam();

        exam.setDateOfExam(dateOfExam);
        exam.setDuration(duration);
        exam.setStudent(student);
        exam.setSubject(subject);

        return exam;
    }
}

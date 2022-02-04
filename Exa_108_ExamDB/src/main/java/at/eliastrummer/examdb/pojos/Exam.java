package at.eliastrummer.examdb.pojos;

import at.eliastrummer.examdb.utils.DateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exam")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false)
    private Long examId;

    @Column(name = "dateofexam")
    @JsonSerialize(using = DateSerializer.class)
    private LocalDate dateOfExam;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "student")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Subject subject;
}
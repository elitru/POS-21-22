package at.eliastrummer.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "firstname", length = 80)
    private String firstname;

    @Column(name = "lastname", length = 80)
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "classname")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Classname classname;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Exam> exams;
}
package at.eliastrummer.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "longname", length = 100)
    private String longname;

    @Column(name = "shortname", length = 10)
    private String shortname;

    @Column(name = "written", nullable = false)
    private Boolean written = false;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Exam> exams;
}
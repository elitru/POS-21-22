package at.eliastrummer.examdb.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classname")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Classname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "classname", length = 10)
    private String classname;

    @OneToMany(mappedBy = "classname")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Student> student;
}
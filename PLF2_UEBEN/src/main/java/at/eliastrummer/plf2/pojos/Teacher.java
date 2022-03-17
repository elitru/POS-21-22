package at.eliastrummer.plf2.pojos;

import at.eliastrummer.plf2.utils.XmlDateAdapter;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Teacher {
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @XmlElement(name = "birthdate")
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private LocalDate dateOfBirth;
    @XmlElement(name = "subject")
    @XmlElementWrapper(name = "subjects")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private List<Subject> subjects;
}

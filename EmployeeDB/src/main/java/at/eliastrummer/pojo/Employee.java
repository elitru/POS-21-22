package at.eliastrummer.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_no", nullable = false)
    @GeneratedValue
    @JsonIgnore
    private int employeeNo;

    @Column(name = "first_name", nullable = false, length = 14)
    @NonNull
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 16)
    @NonNull
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonDeserialize(using = GenderDeserializer.class)
    @NonNull
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonProperty("birthDate")
    @NonNull
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    @JsonIgnore
    @ToString.Exclude
    private Department department;

}

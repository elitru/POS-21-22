package at.eliastrummer.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_no")
    @JsonProperty("emp_no")
    private Long employeeNo;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Birthdate cannot be empty")
    @JsonProperty("birthDate")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "first_name", nullable = false, length = 14)
    @NotNull(message = "Firstname cannot be empty")
    @Size(min = 1, max = 14, message = "Firstname must be between 1 nad 124 characters long.")
    @JsonProperty("firstname")
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 16)
    @NotNull(message = "Lastname cannot be empty")
    @Size(min = 1, max = 14, message = "Lastname must be between 1 nad 124 characters long.")
    @JsonProperty("lastname")
    private String lastname;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender cannot be empty")
    @JsonProperty("gender")
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dept_no")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Department department;
}

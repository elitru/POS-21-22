package at.eliastrummer.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    @Column(name = "dept_no", length = 4)
    @JsonProperty("number")
    private String deptNo;

    @Column(name = "dept_name", nullable = false, length = 40, unique = true)
    @JsonProperty("name")
    private String deptName;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "emp_no")
    @JsonProperty("deptManager")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee deptManager;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    @JsonProperty("employees")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Employee> employees;
}

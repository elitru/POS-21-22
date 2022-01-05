package at.eliastrummer.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue
    @Column(name = "dept_no", length = 4)
    @JsonIgnore
    private int deptNo;

    @JsonProperty("name")
    private String deptName;

    @OneToOne
    @JoinColumn(name = "emp_no")
    @JsonProperty("deptManager")
    private Employee deptManager;

    @OneToMany(mappedBy = "department", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonProperty("employees")
    @ToString.Exclude
    private List<Employee> employees;
}

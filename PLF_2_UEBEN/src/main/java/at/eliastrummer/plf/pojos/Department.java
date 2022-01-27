package at.eliastrummer.plf.pojos;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Entity(name = "dept")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Department {
    @Id
    private Integer deptno = new Random().nextInt();
    @Column(length = 50)
    private String dname;
    @Column(name = "loc", length = 50)
    private String location;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Employee> employees;

    public String toFormattedName() {
        return dname.substring(0, 1).toUpperCase() + dname.substring(1).toLowerCase();
    }
}

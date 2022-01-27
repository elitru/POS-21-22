package at.eliastrummer.plf.pojos;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Entity(name = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Id
    @Column(name = "empno")
    private Integer empNo = new Random().nextInt();
    @Column(name = "ename", length = 50)
    private String lastname;
    @Column(length = 30)
    private String job;
    @Column(name = "sal")
    private Integer salary;
    private Integer comm;
    @Column(name = "hiredate")
    private LocalDate hireDate;

    @OneToOne
    @JoinColumn(name = "mgr")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "deptno")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    @Transient
    private String rawHireDate;

    public String toFormattedName() {
        return lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase();
    }

    public String toFormattedJob() {
        return job.substring(0, 1).toUpperCase() + job.substring(1).toLowerCase();
    }

    public String toFormattedHireDate() {
        return DTF.format(hireDate);
    }

    public String toFormattedSalary() {
        return String.format("€ %,.2f", salary * 1.0);
    }
}

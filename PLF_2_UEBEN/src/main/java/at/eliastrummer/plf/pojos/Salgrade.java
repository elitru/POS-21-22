package at.eliastrummer.plf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

@Entity(name = "salgrade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salgrade {
    @Id
    @Column(name = "grade")
    private Integer gradeId = new Random().nextInt();
    private Integer losal;
    private Integer hisal;
}

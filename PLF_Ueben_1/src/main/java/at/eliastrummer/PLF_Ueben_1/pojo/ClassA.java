package at.eliastrummer.PLF_Ueben_1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassA implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "class_b_id")
    private ClassB classB;
}

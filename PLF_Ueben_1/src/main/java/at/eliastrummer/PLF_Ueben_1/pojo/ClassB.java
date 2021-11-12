package at.eliastrummer.PLF_Ueben_1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassB {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "classB")
    private ClassA classA;
}

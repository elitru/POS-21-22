package at.eliastrummer.plf2.pojos;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Subject {
    @XmlAttribute(name = "id")
    @Id
    private Integer id;
    private String name;
}

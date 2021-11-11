package at.eliastrummer.PLF_Ueben_1.pojo;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_entry")
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = "count", query = "SELECT COUNT(p) FROM Product p")
})
public class Product {
    @Id
    @Column(name = "product_id")
    @XmlAttribute(name = "id")
    private long id;

    @Column(name = "product_name")
    @XmlElement(name = "name")
    private String name;

    @Column(name = "release_date")
    @XmlElement(name = "release")
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private LocalDate releaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product_entry",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            }
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @XmlTransient
    private List<Order> orders = new ArrayList<>();
}

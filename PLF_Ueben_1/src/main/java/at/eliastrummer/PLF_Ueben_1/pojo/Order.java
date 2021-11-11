package at.eliastrummer.PLF_Ueben_1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "\"order\"")
@IdClass(OrderId.class)
public class Order {
    @Id
    @Column(name = "first_id")
    private long secondId;

    @Id
    @Column(name = "second_id")
    private long firstId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;
}

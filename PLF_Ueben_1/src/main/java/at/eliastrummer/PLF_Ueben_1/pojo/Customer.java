package at.eliastrummer.PLF_Ueben_1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "customer", query = "SELECT DISTINCT c FROM Customer c JOIN c.orders o JOIN o.products p WHERE p.id = 1")
})
public class Customer implements Serializable  {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    @JsonProperty
    private long id;

    @Column(name = "first_name", length = 50)
    @JsonProperty
    private String firstname;

    @Column(name = "last_name", length = 50)
    @JsonProperty
    private String lastname;

    @Column(name = "birthdate")
    @JsonProperty("birth_date")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();
}

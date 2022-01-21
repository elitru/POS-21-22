package at.eliastrummer.customerdb.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NamedQueries({
        @NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c) FROM customer c"),
        @NamedQuery(name = "Customer.findFromCountry", query = "SELECT c FROM customer c WHERE c.address.country.countryCode = :country"),
        @NamedQuery(name = "Customer.findYears", query = "SELECT DISTINCT EXTRACT(YEAR FROM c.since) FROM customer c")
})
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long customerId;
    @Column(length = 100)
    private String firstname;
    @Column(length = 100)
    private String lastname;
    @Column(nullable = false)
    private Character gender;
    @Column(nullable = false)
    private Boolean active;
    @Column
    private String email;
    @Column
    private LocalDate since;

    @ManyToOne()
    @JoinColumn(name = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Address address;
}
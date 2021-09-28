package at.eliastrummer.Exa_103.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Address.countAll", query = "SELECT COUNT(a) FROM address a")
})
public class Address implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "street_name", length = 100)
    private String streetName;

    @Column(name = "street_number", nullable = false)
    private int streetNumber;

    @Column(name = "postal_code", length = 50)
    private String postalCode;

    @Column(length = 100)
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Customer> customers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "country")
    @EqualsAndHashCode.Exclude
    private Country country;
}

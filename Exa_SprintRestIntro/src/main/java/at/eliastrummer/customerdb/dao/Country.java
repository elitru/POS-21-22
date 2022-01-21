package at.eliastrummer.customerdb.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "country")
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Country.countAll", query = "SELECT COUNT(c) FROM country c"),
        @NamedQuery(name = "Country.findByName", query = "SELECT c FROM country c WHERE LOWER(c.countryName) = :country"),
        @NamedQuery(name = "Country.findAll", query = "SELECT c FROM country c")
})
public class Country implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private Long countryId;

    @Column(length = 50, name = "name")
    private String countryName;
    @Column(length = 10, name = "code")
    private String countryCode;

    @OneToMany(mappedBy = "country")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Address> addresses = new ArrayList<>();
}
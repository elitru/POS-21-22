package at.eliastrummer.Exa_103.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "country")
@EqualsAndHashCode
public class Country implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "country_id")
    private long countryId;

    @Column(length = 50, name = "name")
    private String countryName;
    @Column(length = 10, name = "code")
    private String countryCode;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Address> addresses = new ArrayList<>();

    @Override
    public String toString() {
        return "";
    }
}

package at.eliastrummer.plf2.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Entity
@NamedQueries({
        @NamedQuery(
                name = "Hobby.findByNameIgnoreCase",
                query = "SELECT h FROM Hobby h WHERE LOWER(h.name) = LOWER(:name)"
        )
})
public class Hobby implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    @Transient
    private long rawId;

    @Column(length = 200, nullable = false)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "hobby"),
            inverseJoinColumns = @JoinColumn(name = "person"),
            name = "person_hobby"
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Person> people = new ArrayList<>();
}

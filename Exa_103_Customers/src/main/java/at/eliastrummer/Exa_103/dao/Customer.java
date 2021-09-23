package at.eliastrummer.Exa_103.dao;

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
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private long customerId;
    @Column(length = 100)
    private String firstname;
    @Column(length = 100)
    private String lastname;
    @Column(nullable = false)
    private char gender;
    @Column(nullable = false)
    private boolean active;
    @Column
    private String email;
    @Column
    private LocalDate since;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "address")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;
}

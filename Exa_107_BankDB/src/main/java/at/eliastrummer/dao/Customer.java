package at.eliastrummer.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Long customerId;
    private String firstname;
    private String lastname;
    @Column(name = "customer_number")
    private Long customerNumber;
    @Column(length = 10)
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    private LocalDate birthdate;
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "customer_account", joinColumns = {@JoinColumn(name = "customer_id")}, inverseJoinColumns = {@JoinColumn(name = "account_id")})
    private List<Account> accounts;

    private <A extends Account> List<A> getAllAccountsOfType(Class<A> accountType) {
        return this
                .accounts
                .stream()
                .filter(accountType::isInstance)
                .map(accountType::cast)
                .collect(Collectors.toList());
    }

    public List<GiroAccount> getGiroAccounts() {
        return getAllAccountsOfType(GiroAccount.class);
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return getAllAccountsOfType(SavingsAccount.class);
    }
}
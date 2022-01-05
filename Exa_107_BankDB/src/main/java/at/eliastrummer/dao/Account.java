package at.eliastrummer.dao;


import lombok.*;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Account {
    protected static final NumberFormat FORMATTER =  NumberFormat.getCurrencyInstance(Locale.GERMANY);

    @Id
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_number")
    private Long accountNumber;
    private Double balance;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "accounts")
    private List<Customer> customers;

    public String getBalanceFormatted() {
        if (balance == null) {
            return FORMATTER.format(0);
        }
        return FORMATTER.format(balance);
    }
}
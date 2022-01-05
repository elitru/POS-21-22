package at.eliastrummer.dao;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("GIRO")
public class GiroAccount extends Account {
    private Double overdraft;
    @Column(name = "debit_interest")
    private Float debitInterest;
    @Column(name = "credit_interest")
    private Float creditInterest;

    public String getOverdraftFormatted() {
        if (overdraft == null) {
            return FORMATTER.format(0);
        }
        return FORMATTER.format(overdraft);
    }

    public String getDebitInterest() {
        if (this.debitInterest == null) return "";
        return String.format("%.2f %%", this.debitInterest);
    }

    public String getCreditInterest() {
        if (this.creditInterest == null) return "";
        return String.format("%.2f %%", this.creditInterest);
    }
}
package at.eliastrummer.dao;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("SPAR")
public class SavingsAccount extends Account {
    private Double interest;

    public String getInterest() {
        if (interest == null) return "";
        return String.format("%.2f", interest) + "%";
    }
}
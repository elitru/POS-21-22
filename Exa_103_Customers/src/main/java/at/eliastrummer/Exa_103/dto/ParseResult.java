package at.eliastrummer.Exa_103.dto;

import at.eliastrummer.Exa_103.dao.Address;
import at.eliastrummer.Exa_103.dao.Country;
import at.eliastrummer.Exa_103.dao.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParseResult {
    private Set<Address> addresses = new HashSet<>();
    private Set<Customer> customers = new HashSet<>();
    private Set<Country> countries = new HashSet<>();
}
package at.eliastrummer.Exa_103.io;

import at.eliastrummer.Exa_103.dao.Address;
import at.eliastrummer.Exa_103.dao.Country;
import at.eliastrummer.Exa_103.dao.Customer;
import at.eliastrummer.Exa_103.dto.CustomerEntry;
import at.eliastrummer.Exa_103.dto.ParseResult;
import at.eliastrummer.Exa_103.dto.XmlCustomers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class IOHandler {
    private static Customer getCustomerReference(Set<Customer> customers, Customer customer) {
        return customers.stream().filter(c -> c.equals(customer)).findFirst().get();
    }

    private static Address getAddressReference(Set<Address> addresses, Address address) {
        return addresses.stream().filter(a -> a.equals(address)).findFirst().get();
    }

    private static Country getCountryReference(Set<Country> countries, Country country) {
        return countries.stream().filter(c -> c.equals(country)).findFirst().get();
    }

    private static ParseResult parseRawData(List<CustomerEntry> entries) {
        ParseResult result = new ParseResult();

        for (CustomerEntry entry : entries) {
            Customer customer = entry.getCustomer();
            result.getCustomers().add(customer);

            Address address = entry.getAddress();
            if (!result.getAddresses().add(address)) {
                address = getAddressReference(result.getAddresses(), address);
            }

            Country country = entry.getCountry();
            if (!result.getCountries().add(country)) {
                country = getCountryReference(result.getCountries(), country);
            }

            customer.setAddress(address);
            address.getCustomers().add(customer);

            address.setCountry(country);
            country.getAddresses().add(address);
        }

        return result;
    }

    public static ParseResult getXmlData() {
        File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.xml").toFile();
        XmlCustomers customerWrapper = JAXB.unmarshal(file, XmlCustomers.class);
        return parseRawData(customerWrapper.getCustomers());
    }

    public static ParseResult getJsonData() throws IOException {
        File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customers.json").toFile();
        List<CustomerEntry> entries = new ObjectMapper().readValue(file, new TypeReference<List<CustomerEntry>>(){});
        return parseRawData(entries);
    }

    public static void main(String[] args) {
        getXmlData();
    }
}

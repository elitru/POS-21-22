package at.eliastrummer.Exa_103.dto;

import at.eliastrummer.Exa_103.dao.Address;
import at.eliastrummer.Exa_103.dao.Country;
import at.eliastrummer.Exa_103.dao.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
public class CustomerEntry {
    @XmlElement
    @JsonProperty
    private String country;

    @XmlElement(name = "country_code")
    @JsonProperty(value = "country_code")
    private String countryCode;

    @XmlElement
    @JsonProperty
    private String city;

    @XmlElement(name = "postal_code")
    @JsonProperty(value = "postal_code")
    private String postalCode;

    @XmlElement(name = "streetname")
    @JsonProperty(value = "streetname")
    private String streetName;

    @XmlElement(name = "streetnumber")
    @JsonProperty(value = "streetnumber")
    private int streetNumber;

    @XmlElement
    @JsonProperty
    private String firstname;

    @XmlElement
    @JsonProperty
    private String lastname;

    @XmlElement
    @JsonProperty
    private String gender;

    @XmlElement
    @JsonProperty
    private boolean active;

    @XmlElement
    @JsonProperty
    private String email;

    @XmlElement
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate since;

    public Customer getCustomer() {
        return new Customer(0, firstname, lastname, gender.charAt(0), active, email, since, null);
    }

    public Address getAddress() {
        return new Address(0, streetName, streetNumber, postalCode, city, new ArrayList<>(), null);
    }

    public Country getCountry() {
        return new Country(0, country, countryCode, new ArrayList<>());
    }
}

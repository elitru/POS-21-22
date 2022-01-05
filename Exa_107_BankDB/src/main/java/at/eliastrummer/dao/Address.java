package at.eliastrummer.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @Column(name = "address_id")
    private Long addressId;
    @Column(length = 100, name = "streetname")
    private String streetName;
    @Column(length = 10, name = "street_number")
    private String streetNumber;
    @Column(length = 10, name = "zip_code")
    private String zipCode;
    @Column(length = 100)
    private String city;
}
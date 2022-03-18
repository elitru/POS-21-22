package at.eliastrummer.pattern.immutable;

public class Address {
    private String streetName;
    private int zipCode;

    public Address(String streetName, int zipCode) {
        this.streetName = streetName;
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}

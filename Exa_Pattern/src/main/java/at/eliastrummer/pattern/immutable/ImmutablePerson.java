package at.eliastrummer.pattern.immutable;

public final class ImmutablePerson { // make class final
    private final String name;
    private final int age;
    private final Address address;

    public ImmutablePerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = new Address(address.getStreetName(), address.getZipCode());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return new Address(address.getStreetName(), address.getZipCode());
    }
}

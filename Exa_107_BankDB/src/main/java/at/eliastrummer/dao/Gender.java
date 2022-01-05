package at.eliastrummer.dao;

public enum Gender {
    MALE("m"), FEMALE("w");
    private String abbreviation;

    Gender(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
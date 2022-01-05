package at.eliastrummer.dao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getAbbreviation();
    }

    @Override
    public Gender convertToEntityAttribute(String abbreviation) {
        if (abbreviation == null) {
            return null;
        }

        return Stream.of(Gender.values()).filter(g -> g.getAbbreviation().equals(abbreviation)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
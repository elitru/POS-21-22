package at.eliastrummer.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Locale;

public class GenderDeserializer extends StdDeserializer<Gender> {
    protected GenderDeserializer() {
        super(Gender.class);
    }

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String gender = jsonParser.getValueAsString().toLowerCase();
        return gender.equals("m") ? Gender.MALE : Gender.FEMALE;
    }
}

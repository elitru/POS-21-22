package at.eliastrummer.pattern.observer.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdDeserializer<LocalDateTime> {
    private DateTimeFormatter DTF = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(jsonParser.getValueAsString(), DTF);
    }
}

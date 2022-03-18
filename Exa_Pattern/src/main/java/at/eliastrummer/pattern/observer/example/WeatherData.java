package at.eliastrummer.pattern.observer.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WeatherData {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ISO_DATE_TIME;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTime;
    private float temperature;
    private float pressure;
    private float humidity;
    private int windSpeed;
    private int windDirection;

    public String toString() {
        return String.format(
                "%s;%s;%.2f;%.1f;%s;%s",
                DTF.format(dateTime),
                temperature,
                pressure,
                humidity,
                windSpeed,
                windDirection
        );
    }
}

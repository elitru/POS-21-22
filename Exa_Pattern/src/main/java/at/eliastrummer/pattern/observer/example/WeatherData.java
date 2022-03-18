package at.eliastrummer.pattern.observer.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class WeatherData {
    @JsonDeserialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dateTime;
    private float temperature;
    private float pressure;
    private float humidity;
    private int windSpeed;
    private int windDirection;
}

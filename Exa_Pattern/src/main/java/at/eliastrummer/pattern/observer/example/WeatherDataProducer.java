package at.eliastrummer.pattern.observer.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Queue;

public class WeatherDataProducer implements Runnable {
    private File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "weatherdata.json").toFile();
    private List<WeatherData> weatherdata;
    private Queue<WeatherData> queue;

    public WeatherDataProducer(Queue<WeatherData> queue) {
        this.queue = queue;
    }

    private void loadFromFile() {
        try {
            weatherdata = new ObjectMapper().readValue(file, new TypeReference<List<WeatherData>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        loadFromFile();

        synchronized (queue) {
            queue.add(weatherdata.get(0));
        }

        for (int i = 1; i < weatherdata.size(); i++) {
            WeatherData current = weatherdata.get(i);
            LocalDateTime previous = weatherdata.get(i - 1).getDateTime();
            long waitInMilliseconds = ChronoUnit.SECONDS.between(previous, current.getDateTime()) * 1000;
            Thread.sleep(waitInMilliseconds);

            synchronized (queue) {
                queue.add(current);
                queue.notify();
            }
        }
    }
}

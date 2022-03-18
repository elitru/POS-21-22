package at.eliastrummer.pattern.observer.example;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class WeatherDataLogger implements WeatherDataObserver {
    private static File logFile = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "logs.txt").toFile();
    private FileWriter fw;
    private BufferedWriter bw;

    @SneakyThrows
    public void open() {
        fw = new FileWriter(logFile);
        bw = new BufferedWriter(fw);
        bw.write("timestamp;temperature;pressure;humidity;windspeed;windDirection\n");
    }

    @SneakyThrows
    public void close() {
        bw.flush();
        bw.close();
        fw.close();
    }

    @SneakyThrows
    @Override
    public void update(WeatherData weatherdata) {
        bw.append(weatherdata.toString() + "\n");
        bw.flush();
    }
}

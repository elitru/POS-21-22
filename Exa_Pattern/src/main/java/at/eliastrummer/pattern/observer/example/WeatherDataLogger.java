package at.eliastrummer.pattern.observer.example;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class WeatherDataLogger implements WeatherDataObserver {
    private static File logFile = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "logs.txt").toFile();

    @SneakyThrows
    @Override
    public void update(WeatherData weatherdata) {
        FileWriter fw = new FileWriter(logFile);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(weatherdata.toString() + "\n");

        bw.flush();
        bw.close();
        fw.close();
    }
}

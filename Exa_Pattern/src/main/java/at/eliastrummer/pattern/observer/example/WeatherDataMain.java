package at.eliastrummer.pattern.observer.example;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class WeatherDataMain {
    @SneakyThrows
    public static void main(String[] args) {
        Queue<WeatherData> queue = new LinkedList<>();

        WeatherDataPrinter printer = new WeatherDataPrinter();
        WeatherDataLogger logger = new WeatherDataLogger();
        WeatherDataGUI gui = new WeatherDataGUI();
        gui.setVisible(true);
        logger.open();

        WeatherDataConsumer consumer = new WeatherDataConsumer(queue);
        consumer.register(printer);
        consumer.register(logger);
        consumer.register(gui);

        WeatherDataProducer producer = new WeatherDataProducer(queue);

        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);
        consumerThread.start();
        producerThread.start();
        producerThread.join();

        consumerThread.interrupt();
        logger.close();
    }
}

package at.eliastrummer.pattern.observer.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WeatherDataGUI extends JFrame implements WeatherDataObserver {
    private JLabel label;

    public WeatherDataGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLocationRelativeTo(null);
        setSize(600, 150);

        Container container = getContentPane();
        container.setLayout(new GridLayout(1, 1, 4, 4));

        label = new JLabel();
        container.add(label);
    }

    @Override
    public void update(WeatherData weatherdata) {
        label.setText(weatherdata.toString());
    }
}

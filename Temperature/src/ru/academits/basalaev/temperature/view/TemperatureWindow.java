package ru.academits.basalaev.temperature.view;

public interface TemperatureWindow {
    void addTemperatureScale(String temperatureScale);

    boolean removeTemperatureScale(String temperatureScales);

    void start();
}
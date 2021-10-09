package ru.academits.basalaev.temperature.model;

public interface TemperatureModel {
    double convert(double degrees, String scaleFrom, String scaleTo);

    void addTemperatureScale(String temperatureScale, CelsiusDegreesConversion conversion);

    void removeTemperatureScale(String temperatureScale);
}
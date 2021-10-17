package ru.academits.basalaev.temperature.model.scales;

public interface Scale {
    double convertFromCelsius(double degrees);

    double convertToCelsius(double degrees);
}
package ru.academits.basalaev.temperature.model;

import ru.academits.basalaev.temperature.model.scales.Scale;

import java.util.List;

public interface TemperatureModel {
    List<String> getScalesNames();

    Scale getScale(int scaleIndex);

    double convert(double degrees, Scale fromScale, Scale toScale);
}
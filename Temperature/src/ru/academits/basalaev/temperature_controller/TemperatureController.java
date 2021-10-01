package ru.academits.basalaev.temperature_controller;

import ru.academits.basalaev.TemperatureScale;
import ru.academits.basalaev.temperature_model.TemperatureModelImpl;

public class TemperatureController {
    private final TemperatureModelImpl model;

    public TemperatureController(TemperatureModelImpl model) {
        this.model = model;
    }

    public double convertTemperature(double degrees, TemperatureScale scaleFrom, TemperatureScale scaleTo) {
        return model.convert(degrees, scaleFrom, scaleTo);
    }
}
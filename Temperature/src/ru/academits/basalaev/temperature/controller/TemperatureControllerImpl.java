package ru.academits.basalaev.temperature.controller;

import ru.academits.basalaev.temperature.model.TemperatureModel;

public class TemperatureControllerImpl implements TemperatureController {
    private final TemperatureModel model;

    public TemperatureControllerImpl(TemperatureModel model) {
        this.model = model;
    }

    public double convertTemperature(double degrees, String scaleFrom, String scaleTo) {
        return model.convert(degrees, scaleFrom, scaleTo);
    }
}
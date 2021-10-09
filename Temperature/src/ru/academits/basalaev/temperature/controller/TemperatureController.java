package ru.academits.basalaev.temperature.controller;

public interface TemperatureController {
    double convertTemperature(double degrees, String scaleFrom, String scaleTo);
}
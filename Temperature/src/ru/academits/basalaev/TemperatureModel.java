package ru.academits.basalaev;

public interface TemperatureModel {
    double convert(double degrees, TemperatureScale scaleFrom, TemperatureScale scaleTo);
}
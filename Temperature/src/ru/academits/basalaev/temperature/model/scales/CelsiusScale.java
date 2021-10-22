package ru.academits.basalaev.temperature.model.scales;

public class CelsiusScale implements Scale {
    @Override
    public String toString() {
        return "Цельсий";
    }

    @Override
    public double convertFromCelsius(double degrees) {
        return degrees;
    }

    @Override
    public double convertToCelsius(double degrees) {
        return degrees;
    }
}
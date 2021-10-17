package ru.academits.basalaev.temperature.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public String toString() {
        return "фаренгейт";
    }

    @Override
    public double convertFromCelsius(double degrees) {
        return degrees * 1.8 + 32;
    }

    @Override
    public double convertToCelsius(double degrees) {
        return (degrees - 32) / 1.8;
    }
}
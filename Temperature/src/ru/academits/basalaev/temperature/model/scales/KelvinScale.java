package ru.academits.basalaev.temperature.model.scales;

public class KelvinScale implements Scale {
    @Override
    public String toString() {
        return "кельвин";
    }

    @Override
    public double convertFromCelsius(double degrees) {
        return degrees + 273.15;
    }

    @Override
    public double convertToCelsius(double degrees) {
        return degrees - 273.15;
    }
}
package ru.academits.basalaev.temperature_model;

import ru.academits.basalaev.TemperatureModel;
import ru.academits.basalaev.TemperatureScale;

import static ru.academits.basalaev.TemperatureScale.*;

public class TemperatureModelImpl implements TemperatureModel {
    public double convertCelsiusToFahrenheit(double degrees) {
        return degrees * 1.8 + 32;
    }

    public double convertCelsiusToKelvin(double degrees) {
        return degrees + 273.15;
    }

    public double convertFahrenheitToCelsius(double degrees) {
        return (degrees - 32) / 1.8;
    }

    public double convertKelvinToCelsius(double degrees) {
        return degrees - 273.15;
    }

    @Override
    public double convert(double degrees, TemperatureScale scaleFrom, TemperatureScale scaleTo) {
        if (scaleFrom == scaleTo) {
            return degrees;
        }

        if (scaleFrom == FAHRENHEIT) {
            double celsiusDegrees = convertFahrenheitToCelsius(degrees);

            return scaleTo == CELSIUS ? celsiusDegrees : convertCelsiusToKelvin(celsiusDegrees);
        }

        if (scaleFrom == KELVIN) {
            double celsiusDegrees = convertKelvinToCelsius(degrees);

            return scaleTo == CELSIUS ? celsiusDegrees : convertCelsiusToFahrenheit(celsiusDegrees);
        }

        return scaleTo == FAHRENHEIT ? convertCelsiusToFahrenheit(degrees) : convertCelsiusToKelvin(degrees);
    }
}
package ru.academits.basalaev.temperature.model;

import java.util.HashMap;
import java.util.Map;

public class TemperatureModelImpl implements TemperatureModel {
    private final Map<String, CelsiusDegreesConversion> conversionsScale;

    public TemperatureModelImpl() {
        conversionsScale = new HashMap<>();
    }

    @Override
    public void addTemperatureScale(String temperatureScale, CelsiusDegreesConversion conversion) {
        conversionsScale.put(temperatureScale, conversion);
    }

    @Override
    public void removeTemperatureScale(String temperatureScale) {
        conversionsScale.remove(temperatureScale);
    }

    @Override
    public double convert(double degrees, String scaleFrom, String scaleTo) {
        if (scaleFrom.equals(scaleTo)) {
            return degrees;
        }

        if (!scaleFrom.equals("цельсий")) {
            double celsiusDegrees = conversionsScale.get(scaleFrom).convertToCelsius(degrees);

            return scaleTo.equals("цельсий") ? celsiusDegrees : conversionsScale.get(scaleTo).convertFromCelsius(celsiusDegrees);
        }

        return conversionsScale.get(scaleTo).convertFromCelsius(degrees);
    }
}
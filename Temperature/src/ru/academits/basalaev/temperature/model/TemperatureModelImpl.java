package ru.academits.basalaev.temperature.model;

import java.util.HashMap;
import java.util.Map;

public class TemperatureModelImpl implements TemperatureModel {
    private final Map<String, CelsiusDegreesConversion> conventionsScale;

    public TemperatureModelImpl() {
        conventionsScale = new HashMap<>();
    }

    @Override
    public void addTemperatureScale(String temperatureScale, CelsiusDegreesConversion conversion) {
        conventionsScale.put(temperatureScale, conversion);
    }

    @Override
    public void removeTemperatureScale(String temperatureScale) {
        conventionsScale.remove(temperatureScale);
    }

    @Override
    public double convert(double degrees, String scaleFrom, String scaleTo) {
        if (scaleFrom.equals(scaleTo)) {
            return degrees;
        }

        if (!scaleFrom.equals("цельсий")) {
            double celsiusDegrees = conventionsScale.get(scaleFrom).convertToCelsius(degrees);

            return scaleTo.equals("цельсий") ? celsiusDegrees : conventionsScale.get(scaleTo).convertFromCelsius(celsiusDegrees);
        }

        return conventionsScale.get(scaleTo).convertFromCelsius(degrees);
    }
}
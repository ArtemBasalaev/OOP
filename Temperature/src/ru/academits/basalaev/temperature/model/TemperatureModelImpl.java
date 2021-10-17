package ru.academits.basalaev.temperature.model;

import ru.academits.basalaev.temperature.model.scales.Scale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureModelImpl implements TemperatureModel {
    private final List<Scale> scalesList;

    public TemperatureModelImpl(List<Scale> scalesList) {
        this.scalesList = scalesList;
    }

    @Override
    public Scale getScale(int scaleIndex) {
        return scalesList.get(scaleIndex);
    }

    @Override
    public List<String> getScalesNamesList() {
        List<String> namesScalesList = new ArrayList<>();

        for (Scale scale : scalesList) {
            namesScalesList.add(scale.toString());
        }

        return Collections.unmodifiableList(namesScalesList);
    }

    @Override
    public double convert(double degrees, Scale fromScale, Scale toScale) {
        if (fromScale == toScale) {
            return degrees;
        }

        return toScale.convertFromCelsius(fromScale.convertToCelsius(degrees));
    }
}
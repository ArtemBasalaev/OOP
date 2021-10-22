package ru.academits.basalaev.temperature.main;

import ru.academits.basalaev.temperature.model.*;
import ru.academits.basalaev.temperature.model.scales.*;
import ru.academits.basalaev.temperature.view.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scale> scalesList = Arrays.asList(
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        );

        TemperatureModel model = new TemperatureModelImpl(scalesList);

        TemperatureView mainWindow = new SwingTemperatureView(model);
        mainWindow.start();
    }
}
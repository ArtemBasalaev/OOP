package ru.academits.basalaev.temperature.main;

import ru.academits.basalaev.temperature.controller.TemperatureController;
import ru.academits.basalaev.temperature.controller.TemperatureControllerImpl;
import ru.academits.basalaev.temperature.model.CelsiusDegreesConversion;
import ru.academits.basalaev.temperature.model.TemperatureModel;
import ru.academits.basalaev.temperature.model.TemperatureModelImpl;
import ru.academits.basalaev.temperature.view.TemperatureWindow;
import ru.academits.basalaev.temperature.view.TemperatureWindowImpl;

public class Main {
    public static void main(String[] args) {
        TemperatureModel model = new TemperatureModelImpl();

        model.addTemperatureScale("фаренгейт", new CelsiusDegreesConversion() {
            public double convertFromCelsius(double degrees) {
                return degrees * 1.8 + 32;
            }

            public double convertToCelsius(double degrees) {
                return (degrees - 32) / 1.8;
            }
        });

        model.addTemperatureScale("кельвин", new CelsiusDegreesConversion() {
            public double convertFromCelsius(double degrees) {
                return degrees + 273.15;
            }

            public double convertToCelsius(double degrees) {
                return degrees - 273.15;
            }
        });

        TemperatureController controller = new TemperatureControllerImpl(model);

        TemperatureWindow mainWindow = new TemperatureWindowImpl(controller);
        mainWindow.addTemperatureScale("фаренгейт");
        mainWindow.addTemperatureScale("кельвин");
        mainWindow.start();
    }
}
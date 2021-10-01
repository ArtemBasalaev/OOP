package ru.academits.basalaev.temperature_main;

import ru.academits.basalaev.temperature_controller.TemperatureController;
import ru.academits.basalaev.temperature_model.TemperatureModelImpl;
import ru.academits.basalaev.temperature_view.TemperatureWindowImpl;

public class Main {
    public static void main(String[] args) {
        TemperatureModelImpl model = new TemperatureModelImpl();

        TemperatureController controller = new TemperatureController(model);

        TemperatureWindowImpl mainWindow = new TemperatureWindowImpl(controller);
        mainWindow.start();
    }
}
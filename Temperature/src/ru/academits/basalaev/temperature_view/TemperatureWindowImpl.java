package ru.academits.basalaev.temperature_view;

import ru.academits.basalaev.TemperatureScale;
import ru.academits.basalaev.TemperatureWindow;
import ru.academits.basalaev.temperature_controller.TemperatureController;

import javax.swing.*;
import java.awt.*;

import static ru.academits.basalaev.TemperatureScale.*;

public class TemperatureWindowImpl implements TemperatureWindow {
    private JFrame frame;
    private final TemperatureController controller;

    public TemperatureWindowImpl(TemperatureController controller) {
        this.controller = controller;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Temperature");
            frame.setSize(400, 320);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            GridLayout gridLayout = new GridLayout(9, 0);
            frame.setLayout(gridLayout);

            JPanel degreesEnterPanel = new JPanel();
            frame.add(degreesEnterPanel);

            JLabel degreesEnterLabel = new JLabel("Введите значение температуры:");
            degreesEnterPanel.add(degreesEnterLabel);

            JPanel inputFieldPanel = new JPanel();
            frame.add(inputFieldPanel);

            JTextField inputField = new JTextField(5);
            inputFieldPanel.add(inputField);

            JPanel fromTemperatureScalePanel = new JPanel();
            frame.add(fromTemperatureScalePanel);

            JLabel fromTemperatureScaleLabel = new JLabel("Выберите шкалу температур из которой необходим перевод:");
            fromTemperatureScalePanel.add(fromTemperatureScaleLabel);

            JPanel fromTemperatureScaleRadioButtonPanel = new JPanel();
            frame.add(fromTemperatureScaleRadioButtonPanel);

            ButtonGroup fromTemperatureScaleButtonGroup = new ButtonGroup();

            JRadioButton fromCelsiusScale = new JRadioButton("°C", true);
            fromTemperatureScaleButtonGroup.add(fromCelsiusScale);

            JRadioButton fromFahrenheitScale = new JRadioButton("°F", false);
            fromTemperatureScaleButtonGroup.add(fromFahrenheitScale);

            JRadioButton fromKelvinScale = new JRadioButton("°K", false);
            fromTemperatureScaleButtonGroup.add(fromKelvinScale);

            fromTemperatureScaleRadioButtonPanel.add(fromCelsiusScale);
            fromTemperatureScaleRadioButtonPanel.add(fromFahrenheitScale);
            fromTemperatureScaleRadioButtonPanel.add(fromKelvinScale);

            JPanel toTemperatureScalePanel = new JPanel();
            frame.add(toTemperatureScalePanel);

            JLabel toTemperatureScaleLabel = new JLabel("Выберите шкалу температур в которую необходим перевод:");
            toTemperatureScalePanel.add(toTemperatureScaleLabel);

            JPanel toTemperatureScaleRadioButtonPanel = new JPanel();
            frame.add(toTemperatureScaleRadioButtonPanel);

            ButtonGroup toTemperatureScaleButtonGroup = new ButtonGroup();

            JRadioButton toCelsiusScale = new JRadioButton("°C", true);
            toTemperatureScaleButtonGroup.add(toCelsiusScale);

            JRadioButton toFahrenheitScale = new JRadioButton("°F", false);
            toTemperatureScaleButtonGroup.add(toFahrenheitScale);

            JRadioButton toKelvinScale = new JRadioButton("°K", false);
            toTemperatureScaleButtonGroup.add(toKelvinScale);

            toTemperatureScaleRadioButtonPanel.add(toCelsiusScale);
            toTemperatureScaleRadioButtonPanel.add(toFahrenheitScale);
            toTemperatureScaleRadioButtonPanel.add(toKelvinScale);

            JPanel convertPanel = new JPanel();
            frame.add(convertPanel);

            JButton convertButton = new JButton("convert");
            convertButton.setPreferredSize(new Dimension(100, 25));
            convertPanel.add(convertButton);

            JPanel degreesOutputPanel = new JPanel();
            frame.add(degreesOutputPanel);

            JLabel degreesOutputLabel = new JLabel("Результат конвертации температуры:");
            degreesOutputPanel.add(degreesOutputLabel);

            JPanel resultPanel = new JPanel();
            frame.add(resultPanel);

            final JLabel resultLabel = new JLabel("");
            resultPanel.add(resultLabel);

            convertButton.addActionListener(e -> {
                try {
                    double degrees = Double.parseDouble(inputField.getText());

                    TemperatureScale scaleFrom;

                    if (fromCelsiusScale.isSelected()) {
                        scaleFrom = CELSIUS;
                    } else if (fromFahrenheitScale.isSelected()) {
                        scaleFrom = FAHRENHEIT;
                    } else {
                        scaleFrom = KELVIN;
                    }

                    TemperatureScale scaleTo;

                    if (toCelsiusScale.isSelected()) {
                        scaleTo = CELSIUS;
                    } else if (toFahrenheitScale.isSelected()) {
                        scaleTo = FAHRENHEIT;
                    } else {
                        scaleTo = KELVIN;
                    }

                    resultLabel.setText(String.format("%.2f", controller.convertTemperature(degrees, scaleFrom, scaleTo)));
                } catch (NumberFormatException exp1) {
                    JOptionPane.showMessageDialog(frame, "Допустимо вводить только числа, разделителем дробной части должна быть \".\"");
                }
            });
        });
    }
}
package ru.academits.basalaev.temperature.view;

import ru.academits.basalaev.temperature.model.TemperatureModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.String.format;

public class TemperatureWindowImpl implements TemperatureWindow {
    private JFrame frame;
    private final TemperatureModel model;

    public TemperatureWindowImpl(TemperatureModel model) {
        this.model = model;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Перевод температур");
            frame.setSize(400, 380);
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

            JTextField inputField = new JTextField(11);
            inputField.setHorizontalAlignment(JTextField.CENTER);
            inputFieldPanel.add(inputField);

            JPanel fromTemperatureScalePanel = new JPanel();
            frame.add(fromTemperatureScalePanel);

            JLabel fromTemperatureScaleLabel = new JLabel("Выберите шкалу температур из которой необходим перевод:");
            fromTemperatureScalePanel.add(fromTemperatureScaleLabel);

            JPanel fromTemperatureScaleList = new JPanel();
            frame.add(fromTemperatureScaleList);

            JComboBox<String> fromTemperatureScaleComboBox = new JComboBox<>();
            fromTemperatureScaleComboBox.setEditable(true);

            JPanel toTemperatureScalePanel = new JPanel();
            frame.add(toTemperatureScalePanel);

            JLabel toTemperatureScaleLabel = new JLabel("Выберите шкалу температур в которую необходим перевод:");
            toTemperatureScalePanel.add(toTemperatureScaleLabel);

            JPanel toTemperatureScaleList = new JPanel();
            frame.add(toTemperatureScaleList);

            JComboBox<String> toTemperatureScaleComboBox = new JComboBox<>();
            toTemperatureScaleComboBox.setEditable(true);

            List<String> scalesNamesList = model.getScalesNamesList();

            for (String scaleName : scalesNamesList) {
                fromTemperatureScaleComboBox.addItem(scaleName);
                toTemperatureScaleComboBox.addItem(scaleName);
            }

            fromTemperatureScaleList.add(fromTemperatureScaleComboBox);
            toTemperatureScaleList.add(toTemperatureScaleComboBox);

            JPanel convertPanel = new JPanel();
            frame.add(convertPanel);

            JButton convertButton = new JButton("конвертировать");
            convertButton.setPreferredSize(new Dimension(130, 25));
            convertPanel.add(convertButton);

            JPanel degreesOutputPanel = new JPanel();
            frame.add(degreesOutputPanel);

            JLabel degreesOutputLabel = new JLabel("Результат конвертации температуры:");
            degreesOutputPanel.add(degreesOutputLabel);

            JPanel resultPanel = new JPanel();
            frame.add(resultPanel);

            JLabel resultLabel = new JLabel("");
            resultPanel.add(resultLabel);

            convertButton.addActionListener(e -> {
                try {
                    double degrees = Double.parseDouble(inputField.getText());

                    int scaleFromIndex = scalesNamesList.indexOf((String) fromTemperatureScaleComboBox.getSelectedItem());
                    int scaleToIndex = scalesNamesList.indexOf((String) toTemperatureScaleComboBox.getSelectedItem());

                    resultLabel.setText(format("%.2f", model.convert(degrees, model.getScale(scaleFromIndex), model.getScale(scaleToIndex))));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Допустимо вводить только числа, разделителем дробной части должна быть \".\"",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}
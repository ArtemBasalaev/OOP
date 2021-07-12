package ru.academits.basalaev.vector_main;

import ru.academits.basalaev.vector.Vector;

import static ru.academits.basalaev.vector.Vector.getMultiplication;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{3, 2, 5});
        Vector vector2 = new Vector(6, new double[]{10, 9, 0, 4});

        System.out.println("Созданы следующие вектора:");
        System.out.println(vector1);
        System.out.println(vector2);

        vector1.getAddition(vector2);
        System.out.println("Результат сложения вектора со вторым вектором:");
        System.out.println(vector1);

        vector1.getMultiplicationScalar(5);
        System.out.println("Результат умножения вектора на скаляр:");
        System.out.println(vector1);

        vector1.getDifference(vector2);
        System.out.println("Результат вычитания из вектора второго вектора:");
        System.out.println(vector1);

        vector1.setArray(new double[]{3, 2, 5});
        System.out.println("Результат установки нового вектора:");
        System.out.println(vector1);

        System.out.printf("Длина вектора равна length = %.1f%n", vector1.getLength());

        vector1.setValue(15, 2);
        System.out.println("Результат установки компонента вектора по индексу:");
        System.out.println(vector1);

        double innerProduct = getMultiplication(vector1, vector2);
        System.out.println("Результат скалярного произведения векторов:");
        System.out.println(innerProduct);
    }
}
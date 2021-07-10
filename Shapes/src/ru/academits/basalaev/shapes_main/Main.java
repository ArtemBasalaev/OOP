package ru.academits.basalaev.shapes_main;

import ru.academits.basalaev.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Shape[] shapes = new Shape[]{new Square(1.0), new Circle(1.3), new Rectangle(1.3, 1.5),
                    new Triangle(1, 1, 2, 3, 3, 1), new Circle(1.1), new Square(1.2)};

            System.out.println("В массиве хранятся следующие фигуры:");
            System.out.println(Arrays.toString(shapes));

            Arrays.sort(shapes, new MaxAreaComparator());

            System.out.print("Фигура с наибольшей площадью: ");
            System.out.println(shapes[shapes.length - 1]);

            Arrays.sort(shapes, new MaxPerimeterComparator());

            System.out.print("Фигура со вторым по величине периметром: ");
            System.out.println(shapes[shapes.length - 2]);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e);
        }
    }
}
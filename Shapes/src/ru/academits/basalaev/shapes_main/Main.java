package ru.academits.basalaev.shapes_main;

import ru.academits.basalaev.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Shape[] shapes = {
                    new Square(1.0),
                    new Circle(1.3),
                    new Rectangle(1.3, 1.5),
                    new Triangle(0, 0, 0, 5, 5, 0),
                    new Circle(1.1),
                    new Square(1.2)
            };

            System.out.println("В массиве хранятся следующие фигуры:");
            System.out.println(Arrays.toString(shapes));

            Arrays.sort(shapes, new AreaComparator());
            System.out.printf("Фигура с наибольшей площадью: %s, S = %.1f%n", shapes[shapes.length - 1],
                    shapes[shapes.length - 1].getArea());

            Arrays.sort(shapes, new PerimeterComparator());
            System.out.printf("Фигура со вторым по величине периметром: %s, P = %.4f%n", shapes[shapes.length - 2],
                    shapes[shapes.length - 2].getPerimeter());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e);
        }
    }
}
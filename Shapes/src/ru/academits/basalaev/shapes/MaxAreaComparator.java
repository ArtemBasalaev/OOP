package ru.academits.basalaev.shapes;

import java.util.Comparator;

public class MaxAreaComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        double compare = shape1.getArea() - shape2.getArea();

        if (compare > 0) {
            return 1;
        }

        if (compare < 0) {
            return -1;
        }

        return 0;
    }
}
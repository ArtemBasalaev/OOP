package ru.academits.basalaev.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Значения сторон прямоугольника должны быть положительными числами, " +
                    "переданные значения width = " + width + " height = " + height);
        }

        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Значение стороны прямоугольника должно быть положительным числом, " +
                    "переданное значение width = " + width);
        }

        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Значение стороны прямоугольника должно быть положительным числом, " +
                    "переданное значение height = " + height);
        }

        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник со сторонами width = %.1f; height = %.1f", width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        return (rectangle.width == width) && (rectangle.height == height);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);

        return hash;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
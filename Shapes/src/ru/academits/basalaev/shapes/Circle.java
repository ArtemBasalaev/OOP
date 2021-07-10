package ru.academits.basalaev.shapes;

import java.util.Objects;

public class Circle implements Shape{
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Значение радиуса должно быть положительным числом");
        }

        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Значение радиуса должно быть положительным числом");
        }

        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("окружность с радиусом %.2f", radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;

        return (circle.radius == radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    public double getWidth() {
        return 2 * radius;
    }

    public double getHeight() {
        return 2 * radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;

    }
}
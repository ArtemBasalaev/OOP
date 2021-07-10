package ru.academits.basalaev.shapes;

import java.util.Objects;

public class Rectangle implements Shape {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        if (sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Значения сторон прямоугольника должны быть положительными числами");
        }

        this.sideA = sideA;
        this.sideB = sideB;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        if (sideA <= 0) {
            throw new IllegalArgumentException("Значение стороны прямоугольника должно быть положительным числом");
        }

        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        if (sideB <= 0) {
            throw new IllegalArgumentException("Значение стороны прямоугольника должно быть положительным числом");
        }

        this.sideB = sideB;
    }

    @Override
    public String toString() {
        return String.format("прямоугольник со сторонами A = %.2f; B = %.2f", sideA, sideB);
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

        return (rectangle.sideA == sideA) && (rectangle.sideB == sideB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }

    public double getWidth() {
        return sideA;
    }

    public double getHeight() {
        return sideB;
    }

    public double getArea() {
        return sideA * sideB;
    }

    public double getPerimeter() {
        return 2 * sideA + 2 * sideB;
    }
}
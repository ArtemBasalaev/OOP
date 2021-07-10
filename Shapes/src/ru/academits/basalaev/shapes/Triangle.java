package ru.academits.basalaev.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    private boolean isTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (x1 - x3) * (y2 - y3) != (x2 - x3) * (y1 - y3);
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        if (!isTriangle(x1, y1, x2, y2, x3, y3)) {
            throw new IllegalArgumentException("Точки лежат на одной прямой. Фигура не треугольник");
        }

        this.y3 = y3;
    }

    @Override
    public String toString() {
        return String.format("треугольник со сторонами A = %.2f; B = %.2f; C = %.2f", getSideA(), getSideB(), getSideC());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return (triangle.x1 == x1) && (triangle.y1 == y1) && (triangle.x2 == x2) && (triangle.y2 == y2)
                && (triangle.x3 == x3) && (triangle.y3 == y3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }

    public double getWidth() {
        double max = Math.max(x1, Math.max(x2, x3));
        double min = Math.min(x1, Math.min(x2, x3));

        return max - min;
    }

    public double getHeight() {
        double max = Math.max(y1, Math.max(y2, y3));
        double min = Math.min(y1, Math.min(y2, y3));

        return max - min;
    }

    public double getSideA() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getSideB() {
        return Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
    }

    public double getSideC() {
        return Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - getSideA()) * (semiPerimeter - getSideB()) * (semiPerimeter - getSideC()));
    }

    public double getPerimeter() {
        return getSideA() + getSideB() + getSideC();
    }
}
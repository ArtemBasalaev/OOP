package ru.academits.basalaev.shapes;

import java.util.Objects;

public class Square implements Shape{
    private double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом");
        }

        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом");
        }

        this.side = side;
    }

    @Override
    public String toString() {
        return String.format("квадрат со стороной: %.2f", side);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return (square.side == side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }

    public double getWidth() {
        return side;
    }

    public double getHeight() {
        return side;
    }

    public double getArea() {
        return Math.pow(side, 2);
    }

    public double getPerimeter() {
        return 4 * side;
    }
}
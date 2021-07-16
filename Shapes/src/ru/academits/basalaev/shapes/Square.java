package ru.academits.basalaev.shapes;

public class Square implements Shape {
    private double edgeLength;

    public Square(double edgeLength) {
        if (edgeLength <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом, " +
                    "переданное значение edge = " + edgeLength);
        }

        this.edgeLength = edgeLength;
    }

    public double getEdgeLength() {
        return edgeLength;
    }

    public void setEdgeLength(double edgeLength) {
        if (edgeLength <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом, " +
                    "переданное значение edge = " + edgeLength);
        }

        this.edgeLength = edgeLength;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной: edge = %.1f", edgeLength);
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

        return square.edgeLength == edgeLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Double.hashCode(edgeLength);

        return hash;
    }

    @Override
    public double getWidth() {
        return edgeLength;
    }

    @Override
    public double getHeight() {
        return edgeLength;
    }

    @Override
    public double getArea() {
        return edgeLength * edgeLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * edgeLength;
    }
}
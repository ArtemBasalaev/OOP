package ru.academits.basalaev.shapes;

public class Square implements Shape {
    private double edge;

    public Square(double edge) {
        if (edge <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом, " +
                    "переданное значение edge = " + edge);
        }

        this.edge = edge;
    }

    public double getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        if (edge <= 0) {
            throw new IllegalArgumentException("Значение стороны квадрата должно быть положительным числом, " +
                    "переданное значение edge = " + edge);
        }

        this.edge = edge;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной: edge = %.1f", edge);
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

        return square.edge == edge;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = hash * prime + Double.hashCode(edge);

        return hash;
    }

    @Override
    public double getWidth() {
        return edge;
    }

    @Override
    public double getHeight() {
        return edge;
    }

    @Override
    public double getArea() {
        return Math.pow(edge, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * edge;
    }
}
package ru.academits.basalaev.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int elementsCount) {
        if (elementsCount <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть <=0, передано значение n = " + elementsCount);
        }

        elements = new double[elementsCount];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        elements = Arrays.copyOf(vector.elements, vector.elements.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, array = null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан массив нулевой длины");
        }

        elements = Arrays.copyOf(array, array.length);
    }

    public Vector(int elementsCount, double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, array = null");
        }

        if (elementsCount <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть <= 0, n = " + elementsCount);
        }

        elements = Arrays.copyOf(array, elementsCount);
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Arrays.toString(elements));

        sb.setCharAt(0, '{');
        sb.setCharAt(sb.length() - 1, '}');

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(elements);

        return hash;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] += vector.elements[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        if (elements.length < vector.elements.length) {
            elements = Arrays.copyOf(elements, vector.elements.length);
        }

        for (int i = 0; i < vector.elements.length; i++) {
            elements[i] -= vector.elements[i];
        }
    }

    public void multiplyScalar(double scalar) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double element : elements) {
            sum += element * element;
        }

        return Math.sqrt(sum);
    }

    public double getElement(int index) {
        if (index >= elements.length || index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс: index = " + index);
        }

        return elements[index];
    }

    public void setElement(int index, double value) {
        if (index >= elements.length || index < 0) {
            throw new IndexOutOfBoundsException("Неверный индекс: index = " + index);
        }

        elements[index] = value;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getSubtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getMultiplication(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        int minElementsCount = Math.min(vector1.elements.length, vector2.elements.length);

        double result = 0;

        for (int i = 0; i < minElementsCount; i++) {
            result += vector1.elements[i] * vector2.elements[i];
        }

        return result;
    }
}
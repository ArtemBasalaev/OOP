package ru.academits.basalaev.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть <=0, передано значение n = " + n);
        }

        this.n = n;
        array = new double[n];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        array = copyArray(vector.array, vector.n);
        n = vector.n;
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, array = null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан массив нулевой длины");
        }

        this.array = copyArray(array, array.length);
        n = array.length;
    }

    public Vector(int n, double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, array = null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан массив нулевой длины");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора не может быть <=0, n = " + n);
        }

        if (n < array.length) {
            throw new IllegalArgumentException("Размерность вектора меньше длинны массива: n = " + n +
                    " длина массива length = " + array.length);
        }

        this.array = copyArray(array, n);
        this.n = n;
    }

    public double[] getArray() {
        return array;
    }

    public void setArray(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, array = null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Передан массив нулевой длины");
        }

        this.array = copyArray(array, array.length);
        n = array.length;
    }

    public int getSize() {
        return n;
    }

    private static double[] copyArray(double[] array, int length) {
        double[] copy = new double[length];
        int minLength = Math.min(array.length, length);

        for (int i = 0; i < minLength; i++) {
            copy[i] = array[i];
        }

        return copy;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
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

        return Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + n;
        hash = prime * hash + Arrays.hashCode(array);

        return hash;
    }

    public void getAddition(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        double[] result;
        int minN = Math.min(n, vector.n);

        if (n >= vector.n) {
            result = array;
        } else {
            result = new double[vector.n];
        }

        for (int i = 0; i < minN; i++) {
            result[i] = array[i] + vector.array[i];
        }

        if (n < vector.n) {
            for (int i = n; i < vector.n; i++) {
                result[i] = vector.array[i];
            }

            array = result;
            n = result.length;
        }
    }

    public void getDifference(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Передана пустая ссылка, vector = null");
        }

        double[] result;
        int minN = Math.min(n, vector.n);

        if (n >= vector.n) {
            result = array;
        } else {
            result = new double[vector.n];
        }

        for (int i = 0; i < minN; i++) {
            result[i] = array[i] - vector.array[i];
        }

        if (n < vector.n) {
            for (int i = n; i < vector.n; i++) {
                result[i] -= vector.array[i];
            }

            array = result;
            n = result.length;
        }
    }

    public void getMultiplicationScalar(int scalar) {
        for (int i = 0; i < n; i++) {
            array[i] = array[i] * scalar;
        }
    }

    public void getRevers() {
        for (int i = 0; i < n; i++) {
            array[i] *= -1;
        }
    }

    public double getLength() {
        double sum = 0;

        for (int i = 0; i < n; i++) {
            sum += array[i] * array[i];
        }

        return Math.sqrt(sum);
    }

    public double getValue(int index) {
        if (index > n - 1 || index < 0) {
            throw new IllegalArgumentException("Неверный индекс: index = " + index);
        }

        return array[index];
    }

    public void setValue(double value, int index) {
        if (index > n - 1 || index < 0) {
            throw new IllegalArgumentException("Неверный индекс: index = " + index);
        }

        array[index] = value;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }

        Vector vector = new Vector(vector1);
        vector.getAddition(vector2);

        return vector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }

        Vector vector = new Vector(vector1);
        vector.getDifference(vector2);

        return vector;
    }

    public static double getMultiplication(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("Передана пустая ссылка");
        }

        int minN = Math.min(vector1.n, vector2.n);

        double result = 0;

        for (int i = 0; i < minN; i++) {
            result += vector1.array[i] * vector2.array[i];
        }

        return result;
    }
}
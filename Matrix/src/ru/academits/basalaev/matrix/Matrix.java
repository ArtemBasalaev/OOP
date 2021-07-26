package ru.academits.basalaev.matrix;

import ru.academits.basalaev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0, передано значение rowsCount = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть > 0, передано значение columnsCount = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0, размер массива = " + array.length);
        }

        int columnsCount = getMaxSubArrayLength(array);

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице должно быть > 0, длина подмассивов = " + columnsCount);
        }

        rows = new Vector[array.length];

        Vector emptyVector = new Vector(columnsCount);

        for (int i = 0; i < array.length; i++) {
            if (array[i].length == 0) {
                rows[i] = new Vector(columnsCount);
            } else {
                rows[i] = new Vector(array[i]);
                rows[i].add(emptyVector);
            }
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть > 0, размер массива векторов = " + vectors.length);
        }

        rows = new Vector[vectors.length];

        int columnsCount = getMaxVectorSize(vectors);
        Vector emptyVector = new Vector(columnsCount);

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = Vector.getSum(emptyVector, vectors[i]);
        }
    }

    private static int getMaxVectorSize(Vector[] vectors) {
        int maxVectorSize = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (maxVectorSize < vectors[i].getSize()) {
                maxVectorSize = vectors[i].getSize();
            }
        }

        return maxVectorSize;
    }

    private static int getMaxSubArrayLength(double[][] array) {
        int maxSubArrayLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxSubArrayLength < array[i].length) {
                maxSubArrayLength = array[i].length;
            }
        }

        return maxSubArrayLength;
    }

    private static void checkSizeEquals(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() && matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы имеют разный размер:"
                    + " размер первой матрицы " + matrix1.getRowsCount() + " х " + matrix1.getColumnsCount()
                    + ", размер второй матрицы " + matrix2.getRowsCount() + " х " + matrix2.getColumnsCount());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Vector vector : rows) {
            sb.append(vector).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");

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

        Matrix matrix = (Matrix) o;

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(rows);

        return hash;
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 <= index < " + rows.length + " передано значение: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 <= index < " + rows.length + " передано значение: " + index);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 <= index < " + getColumnsCount() + " передано значение: " + index);
        }

        double[] column = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            column[i] = rows[i].getElement(index);
        }

        return new Vector(column);
    }

    public void transpose() {
        int columnsCount = getColumnsCount();

        Vector[] vectors = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            vectors[i] = getColumn(i);
        }

        rows = vectors;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new UnsupportedOperationException("Матрица не квадратная" +
                    ", количество строк = " + rows.length + ", количество колонок = " + getColumnsCount());
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        }

        if (rows.length == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        Matrix subMatrix = new Matrix(rows.length - 1, rows.length - 1);

        double matrixDeterminant = 0;
        int sign = 1;

        for (int i = 0; i < rows.length; i++) {
            for (int j = 1, k = 0; j < rows.length; j++, k++) {
                for (int m = 0, n = 0; m < rows[j].getSize(); m++) {
                    if (i == m) {
                        continue;
                    }

                    double element = rows[j].getElement(m);
                    subMatrix.rows[k].setElement(n, element);

                    n++;
                }
            }

            matrixDeterminant += sign * rows[0].getElement(i) * subMatrix.getDeterminant();
            sign *= -1;
        }

        return matrixDeterminant;
    }

    public Vector getProductByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        int vectorSize = vector.getSize();

        if (getColumnsCount() != vectorSize) {
            throw new IllegalArgumentException("Размерность вектора не равна количеству столбцов матрицы." +
                    " Размерность вектора = " + vectorSize + ", количество столбцов матрицы = " + getColumnsCount());
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double vectorElement = Vector.getScalarProduct(rows[i], vector);

            result.setElement(i, vectorElement);
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        checkSizeEquals(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        checkSizeEquals(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        checkSizeEquals(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        checkSizeEquals(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Переданные матрицы нельзя умножить друг на друга:"
                    + " количество столбцов первой матрицы columnsCount = " + matrix1.getColumnsCount() + " не равно"
                    + " количеству строк второй матрицы rowsCount = " + matrix2.getRowsCount());
        }

        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix2.getColumnsCount();

        Matrix result = new Matrix(rowsCount, columnsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                double vectorElement = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));

                result.rows[i].setElement(j, vectorElement);
            }
        }

        return result;
    }
}
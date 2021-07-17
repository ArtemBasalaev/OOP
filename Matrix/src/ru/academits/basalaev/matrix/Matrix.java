package ru.academits.basalaev.matrix;

import ru.academits.basalaev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] row;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть > 0: передано значение: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть > 0: передано значение: " + columnsCount);
        }

        row = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            row[i] = new Vector(columnsCount);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        row = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            row[i] = new Vector(array[i]);
        }

        if (!isMatrix(row)) {
            row = getMatrix(row);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        row = new Vector[matrix.row.length];

        for (int i = 0; i < matrix.row.length; i++) {
            row[i] = new Vector(matrix.row[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        row = new Vector[array.length];

        if (isMatrix(array)) {
            for (int i = 0; i < array.length; i++) {
                row[i] = new Vector(array[i]);
            }
        } else {
            row = getMatrix(array);
        }
    }

    private static boolean isMatrix(Vector[] array) {
        boolean isMatrix = true;
        int rowLength = array[0].getSize();

        for (int i = 1; i < array.length; i++) {
            if (rowLength != array[i].getSize()) {
                isMatrix = false;

                break;
            }
        }

        return isMatrix;
    }

    private static int getRowMaxLength(Vector[] array) {
        int rowMaxLength = array[0].getSize();

        for (int i = 1; i < array.length; i++) {
            if (rowMaxLength < array[i].getSize()) {
                rowMaxLength = array[i].getSize();
            }
        }

        return rowMaxLength;
    }

    private static Vector[] getMatrix(Vector[] array) {
        Vector[] row = new Vector[array.length];

        int rowMaxLength = getRowMaxLength(array);
        Vector emptyVector = new Vector(rowMaxLength);

        for (int i = 0; i < array.length; i++) {
            row[i] = Vector.getSum(array[i], emptyVector);
        }

        return row;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Vector vector : row) {
            sb.append(vector.toString()).append(", ");
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

        return Arrays.equals(row, matrix.row);
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(row);

        return hash;
    }

    public int[] getSize() {
        return new int[]{row.length, row[0].getSize()};
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= row.length) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 < index < " + row.length
                    + " передано значение: " + index);
        }

        return new Vector(row[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (index < 0 || index >= row.length) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 < index < " + row.length
                    + " передано значение: " + index);
        }

        row[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= row[0].getSize()) {
            throw new IndexOutOfBoundsException("Допустимый диапазон индекса 0 < index < " + row[0].getSize()
                    + " передано значение: " + index);
        }

        double[] column = new double[row.length];

        for (int i = 0; i < row.length; i++) {
            column[i] = row[i].getElement(index);
        }

        return new Vector(column);
    }

    public Matrix getTranspose() {
        int columnsCount = row[0].getSize();

        Vector[] result = new Vector[columnsCount];

        for (int i = 0; i < columnsCount; i++) {
            result[i] = getColumn(i);
        }

        return new Matrix(result);
    }

    public void multiplyByScalar(double scalar) {
        for (Vector vector : row) {
            vector.multiplyByScalar(scalar);
        }
    }

    public double getMatrixDeterminant() {
        if (row.length == 1 && row[0].getSize() == 1) {
            return row[0].getElement(0);
        }

        if (row.length == 2 && row[0].getSize() == 2) {
            return row[0].getElement(0) * row[1].getElement(1) - row[0].getElement(1) * row[1].getElement(0);
        }

        Matrix subMatrix = new Matrix(row.length - 1, row.length - 1);

        double matrixDeterminant = 0;
        int sign = 1;

        for (int n = 0; n < row.length; n++) {
            for (int i = 1, k = 0; i < row.length; i++, k++) {
                for (int j = 0, m = 0; j < row[i].getSize(); j++) {
                    if (n == j) {
                        continue;
                    }

                    double element = row[i].getElement(j);
                    subMatrix.row[k].setElement(m, element);

                    m++;
                }
            }

            matrixDeterminant += sign * row[0].getElement(n) * subMatrix.getMatrixDeterminant();
            sign *= -1;
        }

        return matrixDeterminant;
    }

    public Vector getMultiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        int vectorSize = vector.getSize();

        if (row[0].getSize() != vectorSize) {
            throw new ArrayIndexOutOfBoundsException("Размер строки матрицы " + row[0].getSize()
                    + " не равен размеру вектора" + vectorSize);
        }

        Vector result = new Vector(vectorSize);

        for (int i = 0; i < vectorSize; i++) {
            result.setElement(i, Vector.getScalarComposition(row[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (!Arrays.equals(getSize(), matrix.getSize())) {
            throw new ArrayIndexOutOfBoundsException("Матрицы имеют разный размер");
        }

        for (int i = 0; i < row.length; i++) {
            row[i].add(matrix.row[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("В качестве аргумента передана пустая ссылка");
        }

        if (!Arrays.equals(getSize(), matrix.getSize())) {
            throw new ArrayIndexOutOfBoundsException("Матрицы имеют разный размер");
        }

        for (int i = 0; i < row.length; i++) {
            row[i].subtract(matrix.row[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        if (!Arrays.equals(matrix1.getSize(), matrix2.getSize())) {
            throw new IllegalArgumentException("Матрицы имеют разный размер");
        }

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

        if (!Arrays.equals(matrix1.getSize(), matrix2.getSize())) {
            throw new IllegalArgumentException("Матрицы имеют разный размер");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("В качестве первого аргумента передана пустая ссылка");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("В качестве второго аргумента передана пустая ссылка");
        }

        if (matrix1.row[0].getSize() != matrix2.row.length) {
            throw new IllegalArgumentException("Размер переданных матриц не позволяет умножать их между собой");
        }

        int rowCounts = matrix2.row[0].getSize();
        int columnsCount = matrix1.row.length;

        Matrix result = new Matrix(columnsCount, rowCounts);

        for (int i = 0; i < columnsCount; i++) {
            for (int j = 0; j < rowCounts; j++) {
                double elementValue = Vector.getScalarComposition(matrix1.row[i], matrix2.getColumn(j));

                result.row[i].setElement(j, elementValue);
            }
        }

        return result;
    }
}
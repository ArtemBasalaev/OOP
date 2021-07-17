package ru.academits.basalaev.matrix_main;

import ru.academits.basalaev.matrix.Matrix;
import ru.academits.basalaev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] array1 = {
                {2, 5, 6},
                {3, 7, 8},
                {5, 6},
                {0, 0, 0}
        };

        double[][] array2 = {
                {8, 5, 4},
                {7, 3, 2},
                {5, 4, 10},
                {10, 10, 10}
        };

        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);

        System.out.println("Созданы следующие матрицы:");
        System.out.println(matrix1);
        System.out.println(matrix2);

        matrix1.add(matrix2);
        System.out.println("Результат прибавлении к матрице другой матрицы:");
        System.out.println(matrix1);

        matrix1.subtract(matrix2);
        System.out.println("Результат вычитания из матрицы другой матрицы:");
        System.out.println(matrix1);

        System.out.println("Результат сложения двух матриц:");
        System.out.println(Matrix.getSum(matrix1, matrix2));

        matrix1.multiplyScalar(10);
        System.out.println("Результат умножения матрицы на скаляр:");
        System.out.println(matrix1);

        Matrix matrix3 = new Matrix(new double[][]{{1, 2, 1}, {0, 1, 2}});
        Matrix matrix4 = new Matrix(new double[][]{{1, 0}, {0, 1}, {1, 1}});

        System.out.println("Результат умножения матриц: matrix3 * matrix4");
        System.out.println(Matrix.getMultiplication(matrix3, matrix4));

        System.out.println("Результат умножения матриц: matrix4 * matrix3");
        System.out.println(Matrix.getMultiplication(matrix4, matrix3));

        Vector vector = new Vector(new double[]{2, -3, 1});
        System.out.println("Результат умножения матрицы на вектор\n" + matrix1.getMultiplyVector(vector));

        Matrix matrix5 = new Matrix(new double[][]{{1.9, 2.1, 5.1}, {4.2, 6.1, 3.1}, {5.7, 4.2, 2.8}});
        System.out.printf("Определитель матрицы determinant = %.2f%n", matrix5.getMatrixDeterminant());

        System.out.println("Результат транспонирования матрицы\n" + matrix1.getTranspose());

        Vector[] array3 = {new Vector(5), new Vector(3), new Vector(new double[]{1, 10})};
        Matrix matrix6 = new Matrix(array3);
        System.out.println("Создание матрицы из массива векторов:\n" + matrix6);
    }
}
package ru.academits.basalaev.range_main;

import ru.academits.basalaev.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(1.5, 3.2);
        System.out.printf("Начало диапазона: %.2f%n", range1.getFrom());
        System.out.printf("Конец диапазона: %.2f%n", range1.getTo());
        System.out.printf("Длина диапазона: %.2f%n", range1.getLength());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Измените границы диапазона");

        System.out.print("Введите начало диапазона: ");
        range1.setFrom(scanner.nextDouble());

        System.out.print("Введите конец диапазона: ");
        range1.setTo(scanner.nextDouble());

        System.out.println(range1.toString());
        System.out.printf("Длина диапазона: %.2f%n", range1.getLength());

        System.out.print("Введите вещественное число: ");
        double number = scanner.nextDouble();

        boolean isNumberInsideRange = range1.isInside(number);

        if (isNumberInsideRange) {
            System.out.printf("Число: %.2f принадлежит диапазону%n", number);
        } else {
            System.out.printf("Число: %.2f не принадлежит диапазону%n", number);
        }

        Range range2 = new Range(1.5, 4);

        Range[] union = range1.getUnion(range2);
        System.out.println("Результат объединения диапазонов:");

        for (Range range : union) {
            System.out.println(range.toString());
        }

        Range intersection = range1.getIntersection(range2);
        System.out.println("Результат пересечения двух интервалов:");

        if (intersection == null) {
            System.out.println("Пересечения двух интервалов нет");
        } else {
            System.out.println(range2.toString());
        }

        Range[] difference = range1.getDifference(range2);
        System.out.println("Результат разности двух интервалов:");

        if (difference.length == 0) {
            System.out.println("0");
        } else {
            for (Range range : difference) {
                System.out.println(range.toString());
            }
        }
    }
}
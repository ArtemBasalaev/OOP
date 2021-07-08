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

        System.out.printf("Начало диапазона: %.2f%n", range1.getFrom());
        System.out.printf("Конец диапазона: %.2f%n", range1.getTo());
        System.out.printf("Длина диапазона: %.2f%n", range1.getLength());

        System.out.print("Введите вещественное число: ");
        double number = scanner.nextDouble();

        boolean isNumberInsideRange = range1.isInside(number);

        if (isNumberInsideRange) {
            System.out.printf("Число: %.2f принадлежит диапазону", number);
        } else {
            System.out.printf("Число: %.2f не принадлежит диапазону", number);
        }

        Range range2 = new Range(3, 4);

        Range[] union = range1.getRangesUnion(range2);
        System.out.println("Результат объединения диапазонов:");

        for (Range range : union) {
            System.out.printf("Начало диапазона: %.2f%n", range.getFrom());
            System.out.printf("Конец диапазона: %.2f%n", range.getTo());
        }

        Range intersection = range1.getRangesIntersection(range2);
        System.out.println("Результат пересечения двух интервалов:");

        if (intersection == null) {
            System.out.println("Пересечения двух интервалов нет");
        } else {
            System.out.printf("Начало диапазона: %.2f%n", intersection.getFrom());
            System.out.printf("Конец диапазона: %.2f%n", intersection.getTo());
        }

        Range[] asymmetricalDifference = range1.getRangesAsymmetricalDifference(range2);
        System.out.println("Результат несемметричной разности двух интервалов:");

        if (asymmetricalDifference.length == 0) {
            System.out.println("0");
        } else {
            for (Range range : asymmetricalDifference) {
                System.out.printf("Начало диапазона: %.2f%n", range.getFrom());
                System.out.printf("Конец диапазона: %.2f%n", range.getTo());
            }
        }
    }
}
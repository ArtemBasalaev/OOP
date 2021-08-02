package ru.academits.basalaev.ArrayListHome_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> stringListFromFile = getListFromFile("input.txt");

            System.out.println("Результат чтения файла в список");
            System.out.println(stringListFromFile);
        } catch (FileNotFoundException e) {
            System.out.println("Исходный файл не найден либо путь указан не верно");
        }

        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(2, 5, 2, 2, 4, 7, 6, 5, 8));
        removeEvenNumbers(numberList);

        System.out.println("Список после удаления четных чисел:");
        System.out.println(numberList);

        ArrayList<Integer> numbersListWithDuplicates =
                new ArrayList<>(Arrays.asList(8, 5, 5, 7, 255, 1, 4, 8, 8, 3, 3, 3, 5, 255, 1, 4, 2, 2, 7, 7));

        System.out.println("Список после удаления повторяющихся чисел:");
        System.out.println(getListWithoutDuplicates(numbersListWithDuplicates));
    }

    public static ArrayList<String> getListFromFile(String path) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        }

        return list;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getListWithoutDuplicates(ArrayList<Integer> list) {
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>();

        for (Integer listElement : list) {
            if (!listWithoutDuplicates.contains(listElement)) {
                listWithoutDuplicates.add(listElement);
            }
        }

        return listWithoutDuplicates;
    }
}
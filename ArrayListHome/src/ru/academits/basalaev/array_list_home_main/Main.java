package ru.academits.basalaev.array_list_home_main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> linesListFromFile = getLinesListFromFile("input.txt");

            System.out.println("Результат чтения файла в список");
            System.out.println(linesListFromFile);
        } catch (IOException e) {
            System.out.println("Исходный файл не найден либо путь указан не верно");
        }

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(2, 5, 2, 2, 4, 7, 6, 5, 8));
        removeEvenNumbers(numbersList);

        System.out.println("Список после удаления четных чисел:");
        System.out.println(numbersList);

        ArrayList<Integer> numbersListWithDuplicates =
                new ArrayList<>(Arrays.asList(8, 5, 5, 7, 255, 1, 4, 8, 8, 3, 3, 3, 5, 255, 1, 4, 2, 2, 7, 7));

        System.out.println("Список после удаления повторяющихся чисел:");
        System.out.println(getListWithoutDuplicates(numbersListWithDuplicates));
    }

    public static ArrayList<String> getLinesListFromFile(String path) throws IOException {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String string = in.readLine();

            while (string != null) {
                list.add(string);
                string = in.readLine();
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
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(list.size());

        for (Integer e : list) {
            if (!listWithoutDuplicates.contains(e)) {
                listWithoutDuplicates.add(e);
            }
        }

        return listWithoutDuplicates;
    }
}
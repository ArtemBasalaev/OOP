package ru.academits.basalaev.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        }

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 5, 2, 2, 4, 7, 6, 5, 8));

        int list2Size = list2.size();

        for (int i = 0; i < list2Size; i++) {
            if (list2.get(i) % 2 == 0) {
                list2.remove(i);

                list2Size--;
                i--;
            }
        }

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(8, 5, 5, 7, 5, 1, 4, 8, 8, 3, 3, 3, 5, 2, 1, 4, 2, 2, 7, 7));

        ArrayList<Integer> list4 = new ArrayList<>();
        int list4Size = 0;

        for (int listElement : list3) {
            boolean isListElementDuplicate = false;

            for (int j = 0; j < list4Size; j++) {
                if (list4.get(j) == listElement) {
                    isListElementDuplicate = true;

                    break;
                }
            }

            if (!isListElementDuplicate) {
                list4.add(listElement);

                list4Size++;
            }
        }
    }
}
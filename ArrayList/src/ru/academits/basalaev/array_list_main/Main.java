package ru.academits.basalaev.array_list_main;

import ru.academits.basalaev.array_list.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        System.out.println("Созданный список:");
        System.out.println(list1);
        System.out.println("Длина списка = " + list1.size());

        System.out.println("Значение элемента с переданным индексом = " + list1.get(5));

        System.out.println("Старое значение элемента: " + list1.set(5, 5));
        System.out.println("Список после установки значения по индексу:");
        System.out.println(list1);

        list1.add(11);
        System.out.println("Список после добавления элемента в конец:");
        System.out.println(list1);
        System.out.println("Длина списка = " + list1.size());

        list1.add(11, 22);
        System.out.println("Список после добавления элемента по индексу:");
        System.out.println(list1);
        System.out.println("Длина списка = " + list1.size());

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(13, 14, 15, 16, 17, 18, 19, 20, 21, 22));

        list1.addAll(2, list2);
        System.out.println("Список после добавления коллекции по индексу:");
        System.out.println(list1);
        System.out.println("Длина списка = " + list1.size());

        System.out.println("Старое значение элемента = " + list1.set(0, 22));
        System.out.println("Список после установки значения по индексу:");
        System.out.println(list1);

        if (list1.contains(22)) {
            System.out.println("Список содержит переданный элемент");
        } else {
            System.out.println("Список не содержит переданный элемент");
        }

        System.out.println("Список после удаления совпадающих элементов из другой коллекции:");
        list1.removeAll(list2);
        System.out.println(list1);

        list1.add(0, 22);
        list1.add(5, 17);
        list1.add(6, 13);
        System.out.println("Список после добавления элементов по индексу:");
        System.out.println(list1);

        System.out.println("Список после после вызова метода retainAll:");
        list1.retainAll(list2);
        System.out.println(list1);

        if (list2.containsAll(list1)) {
            System.out.println("Список содержит все элементы переданной коллекции");
        } else {
            System.out.println("Список не содержит все элементы переданной коллекции");
        }

        Integer[] array = list2.toArray(new Integer[10]);
        System.out.println("Список преобразованный в массив");
        System.out.println(Arrays.toString(array));

        list2.clear();
    }
}